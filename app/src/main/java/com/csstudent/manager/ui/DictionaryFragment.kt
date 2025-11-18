package com.csstudent.manager.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.csstudent.manager.data.Term
import com.csstudent.manager.data.TermCategory
import com.csstudent.manager.data.TermRepository
import com.csstudent.manager.database.AppDatabase
import com.csstudent.manager.databinding.DialogAddTermBinding
import com.csstudent.manager.databinding.FragmentDictionaryBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null
    private val binding get() = _binding!!
    private lateinit var termAdapter: TermAdapter
    private lateinit var termRepository: TermRepository
    private var currentCategory = TermCategory.ALL.displayName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = AppDatabase.getDatabase(requireContext())
        termRepository = TermRepository(database.termDao())

        setupRecyclerView()
        setupCategorySpinner()
        setupListeners()
        observeTerms()
    }

    private fun setupRecyclerView() {
        termAdapter = TermAdapter(
            onDeleteClick = { term ->
                showDeleteConfirmation(term)
            }
        )
        binding.recyclerTerms.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = termAdapter
        }
    }

    private fun setupCategorySpinner() {
        val categories = TermCategory.values().map { it.displayName }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        binding.spinnerCategory.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentCategory = categories[position]
                observeTerms()
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        }
    }

    private fun setupListeners() {
        binding.fabAddTerm.setOnClickListener {
            showAddTermDialog()
        }

        binding.inputSearch.addTextChangedListener {
            observeTerms()
        }
    }

    private fun observeTerms() {
        val query = binding.inputSearch.text.toString()

        viewLifecycleOwner.lifecycleScope.launch {
            termRepository.searchTerms(query, currentCategory).collectLatest { terms ->
                termAdapter.submitList(terms)
            }
        }
    }

    private fun showAddTermDialog() {
        val dialogBinding = DialogAddTermBinding.inflate(layoutInflater)

        val categories = TermCategory.values().filter { it != TermCategory.ALL }.map { it.displayName }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogBinding.spinnerCategory.adapter = adapter

        AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()
            .apply {
                dialogBinding.btnSave.setOnClickListener {
                    val term = dialogBinding.inputTerm.text.toString()
                    val definition = dialogBinding.inputDefinition.text.toString()
                    val selectedCategory = dialogBinding.spinnerCategory.selectedItem.toString()

                    if (term.isNotEmpty() && definition.isNotEmpty()) {
                        viewLifecycleOwner.lifecycleScope.launch {
                            termRepository.addTerm(
                                Term(
                                    term = term,
                                    definition = definition,
                                    category = selectedCategory
                                )
                            )
                        }
                        dismiss()
                    }
                }

                dialogBinding.btnCancel.setOnClickListener {
                    dismiss()
                }

                show()
            }
    }

    private fun showDeleteConfirmation(term: Term) {
        AlertDialog.Builder(requireContext())
            .setTitle("용어 삭제")
            .setMessage("'${term.term}'을(를) 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    termRepository.deleteTerm(term.id)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
