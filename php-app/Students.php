<?php
class Students {
    public static function getConn() {
        return new PDO("pgsql:host=localhost;dbname=tp", "postgres", "postgres");
    }

    public static function addStudent($pdo, $name, $grade) {
        $stmt = $pdo->prepare("INSERT INTO students (name, grade) VALUES (?, ?)");
        $stmt->execute([$name, $grade]);
    }

    public static function getStudent($pdo, $name) {
        $stmt = $pdo->prepare("SELECT grade FROM students WHERE name = ?");
        $stmt->execute([$name]);
        return $stmt->fetchColumn();
    }

    public static function averageGrade($pdo) {
        $stmt = $pdo->query("SELECT AVG(grade) FROM students");
        return $stmt->fetchColumn();
    }
}
