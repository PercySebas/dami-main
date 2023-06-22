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
import com.example.dami.databinding.FragmentProductosXCateBinding
import com.example.dami.entity.Resultado
import com.example.dami.ui.view.adapter.CateAdapter
import com.example.dami.ui.view.adapter.ProductoAdapter
import com.example.dami.ui.viewmodel.ProductoViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductosXCateFragment : Fragment() {

    private var _binding : FragmentProductosXCateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductoViewModel by activityViewModels {
        ViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductosXCateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idCategoria = arguments?.getInt("idCategoria") ?: 0

        binding.btnNuevo.setOnClickListener {
            val action = ProductosXCateFragmentDirections.actionProductosXCateFragmentToDetProductoFragment(null, idCategoria)
            findNavController().navigate(action)
        }

        viewModel.listarProductos(idCategoria)
        viewModel.listaProducto.observe(viewLifecycleOwner){resultado ->
            when(resultado){
                is Resultado.Exito ->{
                    val lista = resultado.data
                    binding.rvProductos.layoutManager = LinearLayoutManager(requireContext())
                    val metric = resources.displayMetrics
                    val margin = (8f * metric.density).toInt()
                    val layoutParams = binding.rvProductos.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(margin, margin, margin, margin)
                    binding.rvProductos.layoutParams = layoutParams
                    binding.rvProductos.adapter = ProductoAdapter(lista){producto ->
                        println("Producto Seleccionado ${producto.nom_prod}")
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(resources.getString(R.string.confirmacion))
                            .setMessage(resources.getString(R.string.mensaje_ver_prod,producto.nom_prod))
                            .setPositiveButton(resources.getString(R.string.aceptar)){_,_ ->
                                val accion= ProductosXCateFragmentDirections.actionProductosXCateFragmentToDetProductoFragment(producto, idCategoria)
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