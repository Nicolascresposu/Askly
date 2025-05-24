package com.example.askly_prototype

import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.askly_prototype.ui.theme.AsklyprototypeTheme

class MainActivity : ComponentActivity() {
    private lateinit var triangleView: TriangleView
    private lateinit var pregunta: TextView
    private lateinit var respuesta1: TextView
    private lateinit var respuesta2: TextView
    private lateinit var respuesta3: TextView
    private var tapCount = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main);

        triangleView = findViewById<TriangleView>(R.id.triangleView)
        pregunta = findViewById<TextView>(R.id.pregunta)
        respuesta1 = findViewById<TextView>(R.id.respuesta1)
        respuesta2 = findViewById<TextView>(R.id.respuesta2)
        respuesta3 = findViewById<TextView>(R.id.respuesta3)


        triangleView.setOnTapListener(object : TriangleView.OnTapListener {
            override fun onTap() {
                tapCount++
                if (tapCount >= 3) {
                    pregunta.text = "Pasas al lado de un McDonalds"
                    respuesta1.text = "Va al drive-through, y se pide un solo cafe negro"
                    respuesta2.text = "Hay comida en casa"
                    respuesta3.text = "Yey\nMcDonalds\n(!!!)"

                }
            }
        })
        // Esto de quedarse comentado porque sino reemplaza la vista, porque setContent reestablece el contenido lol
//        setContent {
//            AsklyprototypeTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        version = "0.1",
//                        date = "3/7/25",
//                        modifier = Modifier.padding(innerPadding)
//                        //Esto es un comentario de prueba
//                    )
//                }
//            }
//        }
    }
}

//fun onTouchEvent(event: MotionEvent) {
//    if (points.size > 3) {
//        pregunta.text="it worked!";
//    }
//}

@Composable
fun Greeting(version: String, date: String, modifier: Modifier = Modifier) {
    Text(
        text = "Askly first test build. Version $version! Dated $date",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AsklyprototypeTheme {
        Greeting("0.1","3/7/25")
    }
}