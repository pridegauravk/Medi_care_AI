import { useState } from "react";
import axios from "axios";
import "./Chat.css";

export default function Chat() {

    const [message, setMessage] = useState("");
    const [messages, setMessages] = useState([]);
    const [loading, setLoading] = useState(false);

    const sendMessage = async () => {

        if (message.trim() === "") {
            return;
        }

        const currentMessage = message;

        setMessages(prev => [
            ...prev,
            {
                sender: "user",
                text: currentMessage
            }
        ]);

        setMessage("");
        setLoading(true);

        try {

            const response = await axios.post(
                "http://localhost:8080/api/chat",
                {
                    message: currentMessage
                }
            );

            setMessages(prev => [
                ...prev,
                {
                    sender: "bot",
                    text: response.data.reply
                }
            ]);

        } catch (error) {

            setMessages(prev => [
                ...prev,
                {
                    sender: "bot",
                    text: "Unable to connect to the server."
                }
            ]);

        }

        setLoading(false);
    };

    return (
        <div className="chat-container">

            <div className="chat-header">
                🤖 ABC HR Assistant
            </div>

            <div className="chat-body">

                {
                    messages.map((msg, index) => (

                        <div
                            key={index}
                            className={
                                msg.sender === "user"
                                    ? "user-message"
                                    : "bot-message"
                            }
                        >
                            {msg.text}
                        </div>

                    ))
                }

                {
                    loading &&
                    <div className="typing">
                        HR Assistant is typing...
                    </div>
                }

            </div>

            <div className="chat-footer">

                <input
                    type="text"
                    placeholder="Ask HR anything..."
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    onKeyDown={(e) => {
                        if (e.key === "Enter") {
                            sendMessage();
                        }
                    }}
                />

                <button onClick={sendMessage}>
                    Send
                </button>

            </div>

        </div>
    );
}