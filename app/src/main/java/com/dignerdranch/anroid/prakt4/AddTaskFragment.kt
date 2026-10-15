package com.dignerdranch.anroid.prakt4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etTask = view.findViewById<EditText>(R.id.etTask)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val btnBack = view.findViewById<Button>(R.id.btnBack)
        val prefs = PrefsHelper(requireContext())

        btnAdd.setOnClickListener {
                val title = etTask.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(requireContext(),
                    "Введите название дела!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            prefs.addTask(Task(title = title))
            Toast.makeText(requireContext(),
                "Дело добавлено!", Toast.LENGTH_SHORT).show()
            etTask.text.clear()
        }

        btnBack.setOnClickListener {
            (activity as MainActivity).navigateTo(TaskListFragment())
        }
    }
}