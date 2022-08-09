package com.conch.wanandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.conch.wanandroid.base.viewBinding
import com.conch.wanandroid.databinding.FragmentRegisterBinding

/**
 * @author YeJain
 * @date 2022/8/9 16:57
 */
class RegisterFragment : Fragment() {

    private val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}