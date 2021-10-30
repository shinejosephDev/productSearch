package me.life.productsearch.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import me.life.productsearch.databinding.ActivityPrescriptionBinding
import me.life.productsearch.databinding.DialogImagesBinding
import me.life.productsearch.model.PrescriptionRequest
import me.life.productsearch.model.ResultData
import me.life.productsearch.ui.adapter.DialogImageAdapter
import me.life.productsearch.ui.viewmodel.PrescriptionViewModel
import java.io.File


@AndroidEntryPoint
class PrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrescriptionBinding
    private val viewModel: PrescriptionViewModel by viewModels()
    lateinit var dialog: AlertDialog
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val fileUri = data?.data
                val file = fileUri?.let {
                    uriToImageFile(it)
                }
                if (file != null) {
                    dialog.dismiss()
                    binding.blocker.visibility = View.VISIBLE
                    viewModel.fileUpload(file)
                }
            }
        }


    private fun uriToImageFile(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val filePath = cursor.getString(columnIndex)
                cursor.close()
                return File(filePath)
            }
            cursor.close()
        }
        return null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.btnEID.setOnClickListener {
            viewModel.selectedType = PrescriptionViewModel.TYPE_EID
            showDialog(viewModel.eidList)
        }

        binding.btnInsurance.setOnClickListener {
            viewModel.selectedType = PrescriptionViewModel.TYPE_INSURANCE
            showDialog(viewModel.insuranceList)
        }

        binding.btnPres.setOnClickListener {
            viewModel.selectedType = PrescriptionViewModel.TYPE_PRESCRIPTION
            showDialog(viewModel.prescriptionList)
        }

        binding.btnSubmit.setOnClickListener {
            if (binding.etName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.etPhone.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.etFlatNumber.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Flat Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.etBuilding.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Building", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.etStreet.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Building", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (viewModel.eidList.size != 2) {
                Toast.makeText(this, "Add Emirates ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (viewModel.insuranceList.size != 2) {
                Toast.makeText(this, "Add Insurance Card", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (viewModel.prescriptionList.size == 0) {
                Toast.makeText(this, "Add Prescription", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.submit(
                PrescriptionRequest(
                    name = binding.etName.text.toString().trim(),
                    mobile_number = binding.etPhone.text.toString().trim(),
                    flat_number = binding.etFlatNumber.text.toString().trim(),
                    building = binding.etBuilding.text.toString().trim(),
                    street_address = binding.etStreet.text.toString().trim(),
                    emirates_id_back = viewModel.eidList[1],
                    emirates_id_front = viewModel.eidList[0],
                    insurance_card_back = viewModel.insuranceList[1],
                    insurance_card_front = viewModel.insuranceList[0],
                    notes = binding.etNotes.text.toString().trim(),
                    prescription = viewModel.prescriptionList
                )
            )
        }

        viewModel.response.observe(this, {
            run {
                if (it is ResultData.Success) {
                    finish()
                } else if (it is ResultData.Error) {
                    Toast.makeText(
                        this,
                        "Attempt failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        viewModel.fileResponse.observe(this, {
            if (it is ResultData.Success) {
                binding.blocker.visibility = View.GONE
            } else if (it is ResultData.Error) {
                binding.blocker.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Attempt failed. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun showDialog(images: List<String>) {
        val binding = DialogImagesBinding.inflate(layoutInflater)
        dialog = MaterialAlertDialogBuilder(this)
            .setView(binding.root)
            .show()

        val imageAdapter: DialogImageAdapter

        when (viewModel.selectedType) {
            PrescriptionViewModel.TYPE_EID -> {
                imageAdapter = DialogImageAdapter(resultLauncher, images, true)
            }
            PrescriptionViewModel.TYPE_INSURANCE -> {
                imageAdapter = DialogImageAdapter(resultLauncher, images, true)
            }
            else -> {
                imageAdapter = DialogImageAdapter(resultLauncher, images, false)
            }
        }

        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(this@PrescriptionActivity)
            adapter = imageAdapter
        }

        binding.btnDismiss.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnClear.setOnClickListener {
            when (viewModel.selectedType) {
                PrescriptionViewModel.TYPE_EID -> {
                    viewModel.eidList.clear()
                }
                PrescriptionViewModel.TYPE_INSURANCE -> {
                    viewModel.insuranceList.clear()
                }
                PrescriptionViewModel.TYPE_PRESCRIPTION -> {
                    viewModel.prescriptionList.clear()
                }
            }
            dialog.dismiss()
        }
    }

}