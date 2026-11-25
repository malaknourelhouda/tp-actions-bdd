const { getClient, addStudent, getStudent, averageGrade } = require('./students');

let client;

beforeEach(async () => {
    client = await getClient();
    await client.query('TRUNCATE students RESTART IDENTITY');
});

afterEach(async () => {
    await client.end();
});

test('ajouter et lire', async () => {
    await addStudent(client, 'Alice', 15);
    const s = await getStudent(client, 'Alice');
    expect(s.name).toBe('Alice');
    expect(parseFloat(s.grade)).toBe(15.0);
});

test('lire inconnu', async () => {
    const s = await getStudent(client, 'Inconnu');
    expect(s).toBeNull();
});

test('moyenne', async () => {
    await addStudent(client, 'Amel', 10);
    await addStudent(client, 'Karim', 14);
    expect(await averageGrade(client)).toBe(12.0);
});
