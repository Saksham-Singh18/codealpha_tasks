import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Task3_AIChatbot extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    HashMap<String, String> knowledgeBase;

    public Task3_AIChatbot() {

        setTitle("AI Chatbot");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        initializeKnowledgeBase();

        chatArea.append("Bot: Hello! Ask me anything.\n");
    }

    // Knowledge base
    private void initializeKnowledgeBase() {

        knowledgeBase = new HashMap<>();

        knowledgeBase.put("hello", "Hello! How can I help you?");
        knowledgeBase.put("hi", "Hi there!");
        knowledgeBase.put("your name", "I am a Java AI Chatbot.");
        knowledgeBase.put("java", "Java is a powerful object-oriented programming language.");
        knowledgeBase.put("course", "You are studying BCA.");
        knowledgeBase.put("bye", "Goodbye! Have a great day.");
    }

    // NLP Processing
    private String processInput(String input) {

        input = input.toLowerCase();

        for (String key : knowledgeBase.keySet()) {

            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }

        return "Sorry, I don't understand that yet.";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String userInput = inputField.getText();

        if (userInput.isEmpty())
            return;

        chatArea.append("You: " + userInput + "\n");

        String response = processInput(userInput);

        chatArea.append("Bot: " + response + "\n");

        inputField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Task3_AIChatbot().setVisible(true);
        });
    }
}