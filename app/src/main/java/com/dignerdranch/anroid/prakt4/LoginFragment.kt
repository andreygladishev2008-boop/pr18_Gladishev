package com.dignerdranch.anroid.prakt4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<EditText>(R.id.etName)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val prefs = PrefsHelper(requireContext())

        btnLogin.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(),
                    "Введите имя!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.saveUserName(name)
            Toast.makeText(requireContext(),
                "Добро пожаловать, $name!", Toast.LENGTH_SHORT).show()

            (activity as MainActivity).navigateTo(TaskListFragment())
        }
    }
}