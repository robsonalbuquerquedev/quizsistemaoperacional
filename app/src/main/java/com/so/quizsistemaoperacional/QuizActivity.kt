package com.so.quizsistemaoperacional

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var tvSource: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnNext: Button
    private lateinit var progressBarQuiz: ProgressBar
    private lateinit var tvProgressPercent: TextView

    private var currentQuestionIndex = 0
    private var score = 0
    private var answered = false

    // 🔹 Perguntas
    private val questions = listOf(
        Question(
            source = "CESPE / CNPQ",
            text = "As principais características do sistema operacional Windows é ser multitarefa e multiusuário.",
            options = listOf("Errado", "Certo"),
            correctAnswer = 1,
            type = QuestionType.TRUE_FALSE
        ),
        Question(
            source = "IBGP / PREF. / 2019",
            text = "Assinale a alternativa que apresente um tipo de sistema operacional.",
            options = listOf("Windows", "Excel", "Word", "Multitarefa"),
            correctAnswer = 3,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "COMPEC / UFAM / 2022",
            text = "Parte do Sistema Operacional responsável pela interface de comunicação com o usuário é chamada de:",
            options = listOf("Shell", "RAM", "EPROM", "BIOS", "Kernel"),
            correctAnswer = 0,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "BRAVO / INÉDITA / 2025",
            text = "A opção Hibernar ao solicitar para desligar o computador corresponde a qual operação abaixo no Windows:",
            options = listOf("desliga o computador após alguns minutos", "coloca a área de trabalho bloqueada", "desliga o computador e salva tudo o que se estava fazendo no HD, recuperando ao ligá-lo novamente", "ativa o serviço de proteção de tela", "reinicia o computador após alguns minutos"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IBADE / PREF. LINHARES / 2020",
            text = "O sistema operacional que possui código aberto é:",
            options = listOf("Windows Server", "Windows 7", "Vmware", "Linux", "Z/OS"),
            correctAnswer = 3,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IBGP / PREF. ANDRADAS",
            text = "Sistema operacional projetado para dispositivos móveis com tela sensível ao toque, desenvolvido pelo Google.",
            options = listOf("Windows Phone", "Android", "Linux", "Windows 10"),
            correctAnswer = 1,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "QUADRIX / CRP / 2018",
            text = "Diferente de suas versões anteriores, o Windows não é considerado um sistema multitarefas.",
            options = listOf("Certo", "Errado"),
            correctAnswer = 1,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "UNIFIL / PREF. RONDON / 2021",
            text = "Assinale a alternativa que representa um sistema operacional.",
            options = listOf("Google Android", "Telegram", "Microsoft Windows", "Google Windows"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "BRAVO / INÉDITA / 2025",
            text = "O recurso do Windows 10 que, quando acionado, mantém o computador ligado como baixo consumo de energia e com o monitor desligado é chamado:",
            options = listOf("Desconectar", "Suspender", "Desligar", "Repousar", "Reiniciar"),
            correctAnswer = 1,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IDCAP / SAAE IBIRAÇU / 2020",
            text = "Na informática, o que é o conjunto principal de software em um dispositivo que mantém tudo junto e se comunica com o hardware do dispositivo?",
            options = listOf("Planilha Eletrônica", "Sistema Operacional", "Editor de Textos", "Antivírus", "Navegador de Internet"),
            correctAnswer = 1,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "FUNDUNISUL / CRC SC / 2019",
            text = "Um sistema operacional multitarefas permite:",
            options = listOf("A execução de apenas um programa por vez.", "No máximo 5 programas ao mesmo tempo.", "Não tem relação com a execução simultânea de programas.", "A execução de mais de um programa ao mesmo tempo."),
            correctAnswer = 3,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "UNIFIL / PREF. CAMBÉ / 2011",
            text = "Assinale a alternativa que representa um sistema operacional.",
            options = listOf("Microsoft Opertation", "Microsoft Word", "Microsoft Windows", "Microsoft System"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "BRAVO / INÉDITA / 2025",
            text = "Dentre as opções do menu desligar do Windows, qual delas deixa o computador em estado de espera, desligando momentaneamente o monitor, mas mantendo todos os programas em andamento ativos.",
            options = listOf("Bloquear", "Fazer Logoff", "Suspender", "Reiniciar", "Desligar"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "BRAVO / INÉDITA / 2025",
            text = "Que recurso do sistema Operacional Windows permite desligar o computador, salvando os programas em execução, sem perder as informações que não foram salvas?",
            options = listOf("Banco de dados", "Protocolo TCP/IP", "Placa Ethernet", "Particionador de disco", "Hibernar"),
            correctAnswer = 4,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IBGP / PREF. ANDRELÂNDIA / 2019",
            text = "Entre os sistemas abaixo, assinale o que NÃO é um sistema operacional.",
            options = listOf("Linux", "Windows", "MacOS", "Skype"),
            correctAnswer = 3,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IBFC / DETRAN AM / 2022",
            text = "As versões atuais do Windows, Linux e MacOS são considerados sistemas:",
            options = listOf("multiprocessador preeminente", "monotarefa preditiva", "multiusuário perceptivo", "multitarefa preemptiva"),
            correctAnswer = 3,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "CONSULPAM / SURG",
            text = "Não são características do Sistema Operacional Windows:",
            options = listOf("Monousuário", "Multitarefa", "Gráfico", "Código fonte"),
            correctAnswer = 3,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IBGP / PREF. ITABIRA / 2020",
            text = "Com o Windows 10 é possível criar uma conta para cada usuário, pois é um sistema operacional:",
            options = listOf("Arquitetura livre", "Domínio público", "Multiusuário", "Aberto"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "CESPE / CNPQ",
            text = "O Sistema Operacional Windows utiliza multitarefa com preempção, pois permite a execução de diversos programas ao mesmo tempo.",
            options = listOf("Certo", "Errado"),
            correctAnswer = 1,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "QUADRIX / CRMV MS / 2022",
            text = "Os softwares gratuitos, em regra, permitem que o usuário altere o código-fonte.",
            options = listOf("Errado", "Certo"),
            correctAnswer = 0,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "BRAVO / INÉDITA / 2025",
            text = "Ao desligar o computador, o usuário do Wndows pode escolher a opção a ser usada. Se a opção for HIBERNAR, é correto afirmar que o computador:",
            options = listOf("Não será desligado, de modo que, ao acionar o mouse ou teclado, a exibição da área de trabalho e os programas ativos não serão salvos, ou seja, serão perdidos", "será desligado de modo que, quando for reiniciado, a exibição da área de trabalho e os programas ativos não serão salvos, ou seja, serão perdidos", "será desligado de modo que, quando for reiniciado a exibição da área de trabalho e os programas ativos serão exibidos exatamente como estavam antes de hibernar", "Não será desligado de modo que, ao acionar o mouse ou teclado, a área de trabalho e os programas ativos serão exibidos exatamente como estavam antes de hibernar"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "BRAVO / INÉDITA / 2025",
            text = "Entre as maneiras de desligar um computador que utiliza o Microsoft Windows 10, existe uma em que o consumo de energia é muito baixo e o computador é iniciado mais rapidamente, retornando para o ponto em que parou antes do desligamento. Esse estado, disponível em qualquer computador com o Windows 10, é conhecido como:",
            options = listOf("Suspensão", "Desligamento Completo", "Reinício", "Adormecimento", "Desconexão"),
            correctAnswer = 0,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "IBGP / PREF. ITABIRA / 2020",
            text = "Com o Windows 10 é possível executar várias tarefas ao mesmo tempo, pois é um sistema operacional:",
            options = listOf("Multitarefa", "Compartilhado", "Polivalente", "Proprietário"),
            correctAnswer = 0,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "INSTITUTO CONSULPLAN / PREF. FORMIGA / 2020",
            text = "Um Sistema Operacional é composto por dois subsistemas que fazem a interação entre usuário e máquina. Quais são?",
            options = listOf("BIOS e Setup", "Registradores e Memória Cache", "Shell e Kernel", "Processador e Memória"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            source = "OBJETIVA / PREF. SIMÃO DIAS / 2022",
            text = "É conhecido como o mais importante software do computador, que gerencia todos os arquivos e programas.",
            options = listOf("Hardware", "Pacote Office", "Sistema Operacional", "Backup", "Firewall"),
            correctAnswer = 2,
            type = QuestionType.MULTIPLE_CHOICE
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // 🔹 Ligação dos elementos
        tvSource = findViewById(R.id.tvSource)
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        btnNext = findViewById(R.id.btnNext)
        progressBarQuiz = findViewById(R.id.progressBarQuiz)
        tvProgressPercent = findViewById(R.id.tvProgressPercent)

        loadQuestion()

        btnNext.setOnClickListener {
            if (!answered) checkAnswer()
            else moveToNextQuestion()
        }
    }

    private fun loadQuestion() {
        val q = questions[currentQuestionIndex]

        tvSource.text = q.source
        tvQuestionNumber.text = "Questão ${currentQuestionIndex + 1} de ${questions.size}"
        tvQuestion.text = q.text

        for (i in 0 until rgOptions.childCount) {
            val radioButton = rgOptions.getChildAt(i) as RadioButton
            if (i < q.options.size) {
                radioButton.text = q.options[i]
                radioButton.visibility = RadioButton.VISIBLE
                radioButton.setTextColor(Color.parseColor("#333333"))
            } else {
                radioButton.visibility = RadioButton.GONE
            }
        }

        rgOptions.clearCheck()
        answered = false
        btnNext.text = "Responder"

        // Atualiza o progresso visual
        updateProgress()

        tvQuestion.alpha = 0f
        tvSource.alpha = 0f
        tvQuestion.animate().alpha(1f).setDuration(400).start()
        tvSource.animate().alpha(1f).setDuration(400).start()
    }

    private fun checkAnswer() {
        val selectedOptionId = rgOptions.checkedRadioButtonId
        if (selectedOptionId == -1) {
            Toast.makeText(this, "Selecione uma alternativa!", Toast.LENGTH_SHORT).show()
            return
        }

        answered = true
        val selectedIndex = rgOptions.indexOfChild(findViewById(selectedOptionId))
        val q = questions[currentQuestionIndex]

        for (i in 0 until rgOptions.childCount) {
            val radioButton = rgOptions.getChildAt(i) as RadioButton
            when (i) {
                q.correctAnswer -> radioButton.setTextColor(Color.parseColor("#2E7D32"))
                selectedIndex -> if (selectedIndex != q.correctAnswer)
                    radioButton.setTextColor(Color.parseColor("#C62828"))
            }
        }

        if (selectedIndex == q.correctAnswer) score++

        btnNext.text = if (currentQuestionIndex == questions.size - 1)
            "Ver resultado"
        else
            "Próxima questão"

        Handler(mainLooper).postDelayed({
            moveToNextQuestion()
        }, 1000)
    }

    private fun moveToNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) loadQuestion()
        else showResult()
    }

    // 🔹 Atualiza a barra de progresso e o percentual
    private fun updateProgress() {
        val progressPercent = ((currentQuestionIndex.toFloat() / questions.size) * 100).toInt()
        progressBarQuiz.progress = progressPercent
        tvProgressPercent.text = "$progressPercent% concluído"
    }

    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL", questions.size)
        startActivity(intent)
        finish()
    }
}