<?php
use PHPUnit\Framework\TestCase;

require_once __DIR__ . '/../Students.php';

class StudentsTest extends TestCase {
    private $pdo;

    protected function setUp(): void {
        $this->pdo = Students::getConn();
        $this->pdo->exec("TRUNCATE students RESTART IDENTITY");
    }

    public function testAddAndGet() {
        Students::addStudent($this->pdo, "Alice", 15);
        $this->assertEquals(15.0, Students::getStudent($this->pdo, "Alice"));
    }

    public function testGetMissing() {
        $this->assertFalse(Students::getStudent($this->pdo, "Inconnu"));
    }

    public function testMoyenne() {
        Students::addStudent($this->pdo, "Amel", 10);
        Students::addStudent($this->pdo, "Karim", 14);
        $this->assertEquals(12.0, Students::averageGrade($this->pdo));
    }
}
