package com.agilefreaks.freaks_catalog.features.freaks

import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {
    private var freakID: String? = null

    private var _firstName: String? = null
    val firstName: String?
        get() = _firstName

    private var _lastName: String? = null
    val lastName: String?
        get() = _lastName

    private var _role: String? = null
    val role: String?
        get() = _role

    private var _norm: String? = null
    val norm: String?
        get() = _norm

    private var _level: String? = null
    val level: String?
        get() = _level

    private var _description: String? = null
    val description: String?
        get() = _description

    private var _image: Int? = null
    val image: Int?
        get() = _image

    private var _skills: List<String> = mutableListOf()
    val skills: String
        get() = _skills.joinToString(", ")

    private var _projects: List<String> = mutableListOf()
    val projects: String
        get() = _projects.joinToString(", ")

    init {
        getData()
    }

    fun getData() {
        _firstName = "Marian"
        _lastName = "Vanghelie"
        _role = "Android Intern"
        _norm = "Full Time"
        _level = "Beginner"
        _description = "Description"
        _image = 0
        _skills = listOf("Kotlin")
        _projects = listOf("Freaks Catalog")
    }

    fun setID(freakId: String){
        this.freakID = freakId
    }
}
