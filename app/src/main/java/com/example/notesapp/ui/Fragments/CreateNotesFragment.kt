package com.example.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import java.lang.String.format

import java.util.*


class CreateNotesFragment : Fragment() {
    lateinit var binding:FragmentCreateNotesBinding
    var priority:String="1"
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
        binding.pGreen.setOnClickListener {
            priority="1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority="3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority="2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }
        return binding.root
    }
    private fun createNotes(it:View?){
        val title=binding.editTitle.text.toString()
        val subtitle=binding.editSubtitle.text.toString()
        val notes=binding.editNotes.text.toString()
        val d= Date()
        val notesDate:CharSequence= DateFormat.format("MMMM d, yyyy",d.getTime())

        val data= Notes(null,title=title,subtitle=subtitle,notes=notes,date=notesDate.toString(),priority)
        viewModel.addNotes(data)
//        Toast.makeText(
//            this,
//            "Notes created successfully",
//            Toast.LENGTH_SHORT
//        ).show()
        //Log.e("@@@@@", "createNotes: $s")
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

}