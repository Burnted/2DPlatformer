import javax.swing.JFrame

class Window: JFrame() {
    init {
        this.add(GamePanel())

        this.title = "Game"
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isResizable = true
        this.isAlwaysOnTop = true
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }
}