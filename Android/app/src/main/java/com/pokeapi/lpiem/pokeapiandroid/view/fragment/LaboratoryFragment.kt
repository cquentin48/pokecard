package com.pokeapi.lpiem.pokeapiandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pokeapi.lpiem.pokeapiandroid.R
import com.pokeapi.lpiem.pokeapiandroid.view.activity.MainActivity
import com.pokeapi.lpiem.pokeapiandroid.viewmodel.CraftingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_laboratory.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LaboratoryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LaboratoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LaboratoryFragment : Fragment() {
    private var viewModel: CraftingFragmentViewModel = CraftingFragmentViewModel()

    /**
     * Update the title of the view
     */
    private fun setTitle(){
        (this.activity as MainActivity).setActionBarTitle(getString(R.string.fragmentLaboTitle))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laboratory, container, false)
    }

    /**
     * Update the visibility of the elements of the view
     */
    private fun showOrHideElements(visibility:Int, otherVisibility:Int){
        pokemonSpriteImage.visibility = visibility
        nickNamePokemon.visibility = visibility
        pokemonNameCreated.visibility = visibility
        createPokemonCraft.visibility = otherVisibility
        craftPokemonButton.visibility = visibility
        razSettingCraftFragment.visibility = otherVisibility
    }

    /**
     * Manage actions for the resetting button
     */
    private fun manageRazButtonHandler(){
            razSettingCraftFragment.setOnClickListener {
                razSpinners()
                razGeneratedPokemon()
            }
    }

    /**
     * Manage actions for the crafting pokemon button
     */
    private fun manageCraftButtonHandler(){
        craftPokemonButton.setOnClickListener {
            viewModel.getGeneratedData().observe(this, Observer {
                viewModel.addPokemonToCollection(nickNamePokemon.text.toString(), it.id)
            })
            viewModel.getGeneratedData().removeObservers(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle()
        viewModel.loadData()
        updateVisibilityOfResult()
        initBothSpinners()
        manageButtons()
        onCraftingResult()
    }

    /**
     * Manage actions on the result of the craft
     */
    private fun onCraftingResult(){
        generateData()
    }

    /**
     * Handle the visibility of the element of the view based on the action of the player
     */
    private fun updateVisibilityOfResult(){
        viewModel.isPokemonCrafted().observe(this, Observer {
            showOrHideElements(if(it)View.VISIBLE else View.INVISIBLE,
                    if(it)View.INVISIBLE else View.VISIBLE)
        })
    }

    /**
     * Display the result of the craft
     */
    private fun generateData(){
        viewModel.getGeneratedData().observe(this, Observer {
            pokemonNameCreated.text = viewModel.loadPokemonName(it)
            Glide.with(context!!).load(viewModel.loadPokemonSprite(it))
                 .apply(RequestOptions().override(300, 300).circleCrop())
                 .into(pokemonSpriteImage)
        })
    }

    /**
     * Reset the pokemon generated
     */
    private fun razGeneratedPokemon(){
        updateVisibilityOfResult()
    }

    /**
     * Reset the spinners index to 0
     */
    private fun razSpinners(){
        firstTypeSpinner.setSelection(0)
        secondTypeSpinner.setSelection(0)
    }

    /**
     * Manage the actions of the buttons
     */
    private fun manageButtons(){
        manageRazButtonHandler()
        onCreatePokemonCraftButtonClickListener()
        manageCraftButtonHandler()
    }

    /**
     * Manage the randomly generated pokemon action
     */
    private fun onCreatePokemonCraftButtonClickListener(){
        createPokemonCraft.setOnClickListener {
            if(hasTheTypeBeenChoosen()){
                viewModel.generateRandomPokemon(firstTypeSpinner.selectedItemId.toInt(), secondTypeSpinner.selectedItemId.toInt())
                generateData()
            }else{
                displayErrorMessage()
            }
        }
    }

    /**
     * Check if a type had been chosen
     */
    private fun hasTheTypeBeenChoosen():Boolean{
        return firstTypeSpinner.selectedItemId != 0L
    }

    /**
     * Init the types spinners
     */
    private fun initBothSpinners(){
        initSpinnerData(firstTypeSpinner)
        initSpinnerData(secondTypeSpinner)
    }

    /**
     * Display an error message if the user had forgotten to choose a type
     */
    private fun displayErrorMessage(){
        Toast.makeText(context,getString(R.string.notChoosingAnyType),Toast.LENGTH_LONG).show()
    }

    /**
     * Load all types from walkemon API
     */
    private fun initSpinnerData(spinner: Spinner){
        viewModel.getTypesData().observe(this, Observer {
            val adapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, it.typeList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })
    }
}
