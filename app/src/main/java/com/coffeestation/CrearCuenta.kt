package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.coffeestation.databinding.ActivityCrearCuentaBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CrearCuenta : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityCrearCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.id_cliente_web)).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        binding.btnIniciarSesion.setOnClickListener {
            if (binding.eTextoUsuario.text.isNotEmpty() && binding.eTextoContrasena.text.isNotEmpty() ){
                val intent = Intent(this, RegistroCuenta::class.java)
                intent.putExtra("USUARIO", binding.eTextoUsuario.text.toString())
                intent.putExtra("CONTRASENA", binding.eTextoContrasena.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(baseContext, getString(R.string.fallo_completar_espacios), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoogle.setOnClickListener {
            googleSignIn()
        }

        binding.btnFacebook.setOnClickListener {

        }
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle (idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    actualiza(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(cuenta.idToken!!)
            } catch (e: ApiException) {

            }
        }
    }

    private fun actualiza(user: FirebaseUser?) {
        if (user != null){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }
}