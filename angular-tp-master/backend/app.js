// backend/app.js
const express = require("express");
const fs = require("fs");
const path = require("path");
const cors = require("cors");

const app = express();
const PORT = 3000;

// 🔧 Middleware
app.use(express.json());

// 🌐 CORS : autorise explicitement Angular (localhost:4200)
app.use(cors({
  origin: "http://localhost:4200"
}));


const dataDir = path.join(__dirname, "data");


// 🏠 Route test
app.get("/test", (req, res) => {
  res.json({ message: "✅ Backend opérationnel !" });
});

// 📡 Route : liste des transactions
app.get("/api/transactions", (req, res) => {
  const filePath = path.join(dataDir, "transactions.json");
  fs.readFile(filePath, "utf8", (err, data) => {
    if (err) {
      console.error("❌ Erreur lecture fichier transactions.json:", err);
      return res.status(500).json({ error: "Erreur lecture fichier" });
    }
    res.json(JSON.parse(data));
  });
});

// 📡 Route : détail d'une transaction
app.get("/api/transactions/:id", (req, res) => {
  const filePath = path.join(dataDir, `${req.params.id}.json`);
  fs.readFile(filePath, "utf8", (err, data) => {
    if (err) {
      console.error(`❌ Fichier introuvable: ${filePath}`);
      return res.status(404).json({ error: "Transaction introuvable" });
    }
    res.json(JSON.parse(data));
  });
});

// 🚀 Démarrage serveur
app.listen(PORT, () => {
  console.log("🚀 Serveur démarré sur http://localhost:" + PORT);
  console.log("📁 Dossier de données:", dataDir);
  console.log("🌐 CORS activé pour: http://localhost:4200");
  console.log("📡 API disponible sur:");
  console.log("   GET  http://localhost:" + PORT + "/api/transactions");
  console.log("   GET  http://localhost:" + PORT + "/api/transactions/:id");
});

