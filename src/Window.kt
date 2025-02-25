import javax.swing.JFrame

class Window: JFrame() {
    init {
        val gamePanel = GamePanel()
        this.add(gamePanel)
        this.addKeyListener(gamePanel)

        this.title = "Game"
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isResizable = true
        this.isAlwaysOnTop = true
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }
}