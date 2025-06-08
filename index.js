const express = require('express');
const cors = require('cors');

const app = express();

app.use(cors({
  origin: 'http://localhost:5173',
  methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
  credentials: true,  // если используешь куки
}));

app.use(express.json());

// твои маршруты
app.post('/api/auth/register', (req, res) => {
  // логика регистрации
  res.json({ message: 'Регистрация успешна' });
});

app.listen(8080, () => console.log('Server started on port 8080'));
