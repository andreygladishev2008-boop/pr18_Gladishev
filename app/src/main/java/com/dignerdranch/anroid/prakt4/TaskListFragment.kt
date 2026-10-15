package com.dignerdranch.anroid.prakt4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListFragment : Fragment() {

    private lateinit var prefs: PrefsHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = PrefsHelper(requireContext())

        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        val tvEmpty = view.findViewById<TextView>(R.id.tvEmpty)
        val rvTasks = view.findViewById<RecyclerView>(R.id.rvTasks)
        val btnAddTask = view.findViewById<Button>(R.id.btnAddTask)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        val userName = prefs.getUserName()
        tvWelcome.text = "Привет, $userName!"

        val tasks = prefs.getTasks()
        tvEmpty.visibility = if (tasks.isEmpty()) View.VISIBLE else View.GONE

        rvTasks.layoutManager = LinearLayoutManager(requireContext())
        rvTasks.adapter = TaskAdapter(tasks)

        btnAddTask.setOnClickListener {
            (activity as MainActivity).navigateTo(AddTaskFragment())
        }

        btnLogout.setOnClickListener {
            prefs.clearAll()
            (activity as MainActivity).navigateTo(LoginFragment())
        }
    }
}