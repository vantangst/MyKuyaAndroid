package com.mykuya.android.ui.picklocation

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.mykuya.android.R
import com.mykuya.android.utils.extension.show
import kotlinx.android.synthetic.main.app_action_bar.*
import kotlinx.android.synthetic.main.dialog_pick_location.*
import java.io.IOException


class DialogPickLocation(context: Context, var listener: (String) -> Unit = {}) :
    Dialog(context, R.style.FullScreenDialogLight) {


    private lateinit var map: GoogleMap
    private var onCameraIdleListener: OnCameraIdleListener? = null

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_pick_location)
        window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        MapsInitializer.initialize(context)
        mapView.onCreate(this.onSaveInstanceState())
        mapView.onResume()

        initUi()

    }

    private fun initUi() {
        mapView.getMapAsync { googleMap ->
            map = googleMap
            map.setOnCameraIdleListener(onCameraIdleListener)
        }

        btn_back.show()
        btn_back.setOnClickListener {
            dismiss()
        }

        btn_confirm.setOnClickListener {
            val address = tv_title.text.toString()
            if (address.isNotEmpty()) {
                listener.invoke(address)
                dismiss()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.msg_error_address_null),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        configureCameraIdle()
    }

    private fun configureCameraIdle() {
        onCameraIdleListener = OnCameraIdleListener {
            val latLng: LatLng = map.cameraPosition.target
            val geocoder = Geocoder(context)
            try {
                val addressList: List<Address>? =
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if (addressList != null && addressList.isNotEmpty()) {
                    if (!addressList[0].getAddressLine(0)
                            .isNullOrEmpty() && !addressList[0].countryName.isNullOrEmpty()
                    ) {
                        val locality: String = addressList[0].getAddressLine(0)
                        val country: String = addressList[0].countryName
                        tv_title.text = "$locality  $country"
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}