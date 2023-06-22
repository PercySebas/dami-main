package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dami.R
import com.example.dami.databinding.FragmentMainBinding
import com.example.dami.entity.Resultado
import com.example.dami.ui.view.adapter.CateAdapter
import com.example.dami.ui.viewmodel.CategoriaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoriaViewModel by activityViewModels {
        ViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.listarCategorias()
        viewModel.listaCategoria.observe(viewLifecycleOwner){ resultado ->
            when(resultado){
                is Resultado.Exito ->{
                    val lista = resultado.data
                    binding.rvCategoria.layoutManager = LinearLayoutManager(requireContext())
                    val metric = resources.displayMetrics
                    val margin = (8f * metric.density).toInt()
                    val layoutParams = binding.rvCategoria.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(margin, margin, margin, margin)
                    binding.rvCategoria.layoutParams = layoutParams
                    binding.rvCategoria.adapter = CateAdapter(lista){categoria ->
                        println("CLICK ${categoria.id_cat} nombre: ${categoria.nom_cat}")
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(resources.getString(R.string.confirmacion))
                            .setMessage(resources.getString(R.string.mensaje_siguiente,categoria.nom_cat))
                            .setPositiveButton(resources.getString(R.string.aceptar)){_,_ ->
                                val accion= MainFragmentDirections.actionMainFragmentToProductosXCateFragment(categoria.id_cat)
                                findNavController().navigate(accion)
                            }
                            .setNegativeButton(resources.getString(R.string.cancelar),null)
                            .show()
                    }
                }
                is Resultado.Problema ->{
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Error")
                        .setMessage(resultado.error.mensaje)
                        .setPositiveButton("Aceptar", null)
                        .show()
                }
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}