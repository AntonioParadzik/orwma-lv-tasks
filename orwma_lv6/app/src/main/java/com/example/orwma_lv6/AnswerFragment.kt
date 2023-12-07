package com.example.orwma_lv6



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction


class AnswerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_answer, container, false)
        val textView = view.findViewById<TextView>(R.id.textView2)
        textView.text = arguments?.getString("data").toString()

        val natragButton= view.findViewById<Button>(R.id.natragButton)
        natragButton.setOnClickListener {
            
            val fragment = QuestionFragment()

            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_container, fragment)
            fragmentTransaction?.commit()
        }

        return view
    }

}