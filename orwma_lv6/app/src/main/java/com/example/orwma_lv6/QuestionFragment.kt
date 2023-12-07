package com.example.orwma_lv6


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.FragmentTransaction

class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_question, container, false)
        val naprijedButton = view.findViewById<Button>(R.id.naprijedButton)
        naprijedButton.setOnClickListener{
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
            val radioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            val bundle = Bundle()
            bundle.putString("data", radioButton.text.toString())
            val fragment = AnswerFragment()
            fragment.arguments = bundle

            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_container, fragment)
            fragmentTransaction?.commit()
        }

        return view
    }

}