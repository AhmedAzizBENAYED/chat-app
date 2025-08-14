# 🌍 Global Chat Application

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-blue.svg)](https://stomp.github.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Railway](https://img.shields.io/badge/Deployed%20on-Railway-purple.svg)](https://railway.app)

A real-time, globally accessible chat application built with Spring Boot and WebSocket technology. Users from anywhere in the world can join the same chat room and communicate instantly.

## ✨ Features

- 🚀 **Real-time messaging** - Instant message delivery using WebSocket/STOMP protocol
- 🌐 **Global accessibility** - Connect from anywhere with internet access
- 👥 **Multi-user support** - Unlimited concurrent users
- 📱 **Responsive design** - Works on desktop, tablet, and mobile devices
- 🔄 **Auto-reconnection** - Automatic reconnection with exponential backoff
- 🎨 **Modern UI** - Beautiful, gradient-based interface with animations
- 📊 **User counter** - Real-time display of connected users
- 🔔 **Browser notifications** - Desktop notifications for new messages (when tab inactive)
- ⚡ **Lightweight** - Minimal resource usage, fast loading

## 🛠️ Technology Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring WebSocket** - Real-time communication
- **STOMP Protocol** - Simple Text Oriented Messaging Protocol
- **Maven** - Dependency management and build tool

### Frontend
- **HTML5** - Markup structure
- **CSS3** - Styling with modern features (gradients, animations, flexbox)
- **JavaScript (ES6+)** - Client-side functionality
- **Bootstrap 5** - Responsive UI framework
- **SockJS** - WebSocket fallback library
- **STOMP.js** - STOMP client for browsers

### Deployment
- **Railway** - Cloud platform hosting
- **Docker** - Containerization (handled by Railway)
- **HTTPS** - Automatic SSL certificate

## 🚀 Live Demo

**🔗<img width="1912" height="955" alt="image" src="https://github.com/user-attachments/assets/18399e6d-d7f5-42be-8763-f64dfb9cc1ac" />

*Open multiple browser windows or share with friends to test real-time messaging!*

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use included Maven wrapper)
- Git
- Modern web browser with WebSocket support

## 🔧 Local Development

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/chat-app.git
cd chat-app
```

### 2. Build the Application
```bash
# Using Maven wrapper (recommended)
./mvnw clean package

# Or using system Maven
mvn clean package
```

### 3. Run the Application
```bash
# Option 1: Run with Maven
./mvnw spring-boot:run

# Option 2: Run JAR file
java -jar target/app-0.0.1-SNAPSHOT.jar
```

### 4. Access the Application
Open your browser and navigate to: **http://localhost:8080**

## 🏗️ Project Structure

```
chat-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/chat/app/
│       │       ├── GlobalChatApplication.java      # Main application class
│       │       ├── config/
│       │       │   └── WebSocketConfig.java        # WebSocket configuration
│       │       └── controller/
│       │           └── ChatController.java         # Message handling
│       └── resources/
│           ├── templates/
│           │   └── chat.html                       # Frontend interface
│           └── application.properties              # App configuration
├── pom.xml                                         # Maven dependencies
├── system.properties                               # Java version for deployment
└── README.md                                       # This file
```

## 🔧 Configuration

### Application Properties
```properties
server.port=${PORT:8080}                    # Server port (Railway sets PORT env var)
spring.profiles.active=production            # Production profile
spring.websocket.servlet.allowed-origins=*  # CORS settings
```

### WebSocket Configuration
- **Endpoint**: `/chat` - WebSocket connection endpoint
- **Message Broker**: `/topic` - STOMP message destination prefix
- **Application Prefix**: `/app` - Application message destination prefix

## 🌐 Deployment

### Railway (Current)
```bash
# 1. Push to GitHub
git add .
git commit -m "Deploy chat application"
git push origin main

# 2. Connect to Railway
# Go to railway.app → New Project → Deploy from GitHub repo

# 3. Railway automatically:
# - Detects Spring Boot application
# - Builds with Maven
# - Deploys with automatic HTTPS
# - Provides public URL
```

### Alternative Platforms
- **Heroku**: `git push heroku main`
- **Render**: Connect GitHub repo
- **Google Cloud Run**: `gcloud run deploy`
- **AWS Elastic Beanstalk**: Upload JAR file

## 🧪 Testing

### Manual Testing
1. **Single User**: Open application, send messages to yourself
2. **Multiple Users**: Open multiple browser tabs/windows
3. **Network Testing**: Test from different devices/networks
4. **Mobile Testing**: Test responsive design on mobile devices

### Test Scenarios
- ✅ Message sending and receiving
- ✅ Real-time synchronization across multiple clients
- ✅ Connection recovery after network interruption
- ✅ User counter accuracy
- ✅ Message ordering and timestamp display
- ✅ Input validation and error handling

## 📚 API Documentation

### WebSocket Endpoints

#### Connect
- **URL**: `ws://localhost:8080/chat` (local) or `wss://your-domain/chat` (production)
- **Protocol**: SockJS + STOMP

#### Send Message
- **Destination**: `/app/sendMessage`
- **Payload**: 
```json
{
  "sender": "John Doe",
  "content": "Hello, world!"
}
```

#### Receive Messages
- **Subscription**: `/topic/messages`
- **Response**:
```json
{
  "sender": "John Doe",
  "content": "Hello, world!",
  "timestamp": "2024-01-15T10:30:00"
}
```

#### User Count Updates
- **Subscription**: `/topic/usercount`
- **Response**: Integer value of connected users

## 🐛 Troubleshooting

### Common Issues

**WebSocket Connection Failed**
```javascript
// Check browser console for errors
// Ensure correct URL (ws:// for local, wss:// for HTTPS)
// Verify server is running on correct port
```

**Messages Not Appearing**
- Check browser network tab for WebSocket connection
- Verify STOMP subscription is active
- Check server logs for errors

**Build Failures**
```bash
# Clear Maven cache
./mvnw dependency:purge-local-repository

# Rebuild
./mvnw clean install
```

### Performance Optimization
- **Connection Pooling**: Implemented with automatic cleanup
- **Message Batching**: STOMP handles message queuing
- **Heartbeat**: 25-second intervals to maintain connections
- **Reconnection Strategy**: Exponential backoff (2s, 4s, 8s, 16s, 32s)

## 🤝 Contributing

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/amazing-feature`
3. **Commit changes**: `git commit -m 'Add amazing feature'`
4. **Push to branch**: `git push origin feature/amazing-feature`
5. **Open a Pull Request**

### Development Guidelines
- Follow Java naming conventions
- Add comments for complex logic
- Test on multiple browsers
- Ensure mobile responsiveness
- Update documentation for new features

## 👨‍💻 Author

**Ahmed Aziz BEN AYED**
- LinkedIn: (https://linkedin.com/in/yourprofile)](https://www.linkedin.com/in/ahmed-aziz-ben-ayed-079603288/)
- Email: ahmedaziz.benayed03@gmail.com

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Railway for seamless deployment platform
- Bootstrap team for the responsive CSS framework
- STOMP protocol developers for real-time messaging standards

## 📊 Project Status

- ✅ **Core Features**: Complete
- ✅ **Deployment**: Live on Railway
- 🔄 **Future Enhancements**: See Issues tab

### Potential Future Features
- 🔐 User authentication and profiles
- 💾 Message persistence and history
- 🏠 Multiple chat rooms
- 📁 File sharing capabilities
- 🎨 Custom themes and emoji support
- 🔕 Message notifications and sound alerts
- 👮‍♂️ Admin moderation tools

---

⭐ **If you found this project helpful, please give it a star!** ⭐
