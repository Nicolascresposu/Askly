package com.example.askly_prototype

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.askly_prototype.ui.theme.AsklyprototypeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var triangleView: TriangleView
    private lateinit var pregunta: TextView
    private lateinit var respuesta1: TextView
    private lateinit var respuesta2: TextView
    private lateinit var respuesta3: TextView
    private lateinit var ownerAndTarget: TextView
    data class Pregunta(
        val id: Int,
        val pregunta: String = "PlaceHolder question",
        val respuesta1: String = "Placeholder answer 1",
        val respuesta2: String = "Placeholder answer 2",
        val respuesta3: String = "Placeholder answer 3"
        )
    private var preguntas: List<Pregunta> = listOf(
        Pregunta(0, "¿Qué hacer cuando te sientes estresado?", "Respirar profundamente", "Salir a caminar", "Hablar con alguien"),
        Pregunta(1, "¿Cómo mejorar tu productividad?", "Hacer una lista de tareas", "Eliminar distracciones", "Tomar descansos regulares"),
        Pregunta(2, "¿Qué hacer en un día libre?", "Ver una película", "Leer un libro", "Visitar a amigos"),
        Pregunta(3, "¿Cómo ahorrar dinero?", "Reducir gastos innecesarios", "Hacer un presupuesto", "Buscar ofertas"),
        Pregunta(4, "¿Qué hacer para dormir mejor?", "Establecer una rutina", "Evitar pantallas antes de dormir", "Crear un ambiente tranquilo"),
        Pregunta(5, "¿Cómo mantener una conversación?", "Hacer preguntas abiertas", "Escuchar activamente", "Compartir experiencias propias"),
        Pregunta(6, "¿Qué hacer cuando te sientes solo?", "Llamar a un ser querido", "Unirte a un grupo social", "Practicar un hobby"),
        Pregunta(7, "¿Cómo aprender algo nuevo?", "Tomar un curso", "Practicar regularmente", "Buscar un mentor"),
        Pregunta(8, "¿Qué hacer para mantenerte saludable?", "Comer balanceado", "Hacer ejercicio", "Dormir suficiente"),
        Pregunta(9, "¿Cómo manejar el fracaso?", "Aprender de los errores", "No tomarlo personal", "Intentar de nuevo"),
        Pregunta(10, "¿Qué hacer para ser más organizado?", "Usar un calendario", "Priorizar tareas", "Mantener espacios limpios"),
        Pregunta(11, "¿Cómo tomar una decisión difícil?", "Listar pros y contras", "Consultar con expertos", "Dormir sobre ello"),
        Pregunta(12, "¿Qué hacer cuando estás aburrido?", "Probar algo nuevo", "Aprender una habilidad", "Ayudar a alguien"),
        Pregunta(13, "¿Cómo mantener una relación saludable?", "Comunicarse abiertamente", "Pasar tiempo de calidad", "Respetar diferencias"),
        Pregunta(14, "¿Qué hacer para mejorar tu ánimo?", "Escuchar música", "Hacer ejercicio", "Practicar gratitud"),
        Pregunta(15, "¿Cómo prepararte para una entrevista?", "Investigar la empresa", "Practicar respuestas", "Vestir apropiadamente"),
        Pregunta(16, "¿Qué hacer cuando tienes mucho que hacer?", "Priorizar tareas", "Dividir en pasos pequeños", "Pedir ayuda"),
        Pregunta(17, "¿Cómo ser más creativo?", "Buscar inspiración", "Probar nuevas experiencias", "No temer al error"),
        Pregunta(18, "¿Qué hacer para recordar algo importante?", "Anotarlo", "Establecer recordatorios", "Asociarlo con algo"),
        Pregunta(19, "¿Cómo mantener la motivación?", "Establecer metas claras", "Celebrar pequeños logros", "Recordar tu 'por qué'")

    )
    private var tapCount = 0;
    private var actual: Pregunta? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main);

        triangleView = findViewById<TriangleView>(R.id.triangleView)
        pregunta = findViewById<TextView>(R.id.pregunta)
        respuesta1 = findViewById<TextView>(R.id.respuesta1)
        respuesta2 = findViewById<TextView>(R.id.respuesta2)
        respuesta3 = findViewById<TextView>(R.id.respuesta3)
        ownerAndTarget = findViewById<TextView>(R.id.ownerAndTarget)
        triangleView.setOnTapListener(object : TriangleView.OnTapListener {
            override fun onTap() {
                tapCount++
                // 3 en este caso es el numero de jugadores
                if (tapCount % 3 == 0) {
                    actual = preguntas.find { it.id == tapCount / 3 } //Random.nextInt(0,preguntas.size)
                    pregunta.text = actual?.pregunta
                    respuesta1.text = actual?.respuesta1
                    respuesta2.text = actual?.respuesta2
                    respuesta3.text = actual?.respuesta3
                }
            }
        })
    }
}

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