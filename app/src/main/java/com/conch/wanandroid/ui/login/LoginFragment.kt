package com.conch.wanandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.conch.wanandroid.base.viewBinding
import com.conch.wanandroid.databinding.FragmentLoginBinding

/**
 * @author YeJain
 * @date 2022/8/9 16:32
 */
class LoginFragment : Fragment() {


    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


}