const { Client } = require('pg');

async function getClient() {
    const client = new Client({ host: 'localhost', database: 'tp', user: 'postgres', password: 'postgres' });
    await client.connect();
    return client;
}

async function addStudent(client, name, grade) {
    await client.query('INSERT INTO students (name, grade) VALUES ($1, $2)', [name, grade]);
}

async function getStudent(client, name) {
    const res = await client.query('SELECT name, grade FROM students WHERE name = $1', [name]);
    return res.rows.length > 0 ? res.rows[0] : null;
}

async function averageGrade(client) {
    const res = await client.query('SELECT AVG(grade) as avg FROM students');
    return res.rows[0].avg ? parseFloat(res.rows[0].avg) : null;
}

module.exports = { getClient, addStudent, getStudent, averageGrade };
