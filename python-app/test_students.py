import pytest
from students import get_conn, add_student, get_student, average_grade

@pytest.fixture
def conn():
    c = get_conn()
    with c.cursor() as cur:
        cur.execute("TRUNCATE students RESTART IDENTITY;")
    c.commit()
    yield c
    c.close()

def test_add_and_get(conn):
    add_student(conn, "Alice", 15)
    assert get_student(conn, "Alice") == ("Alice", 15.0)

def test_get_missing(conn):
    assert get_student(conn, "Inconnu") is None

def test_moyenne(conn):
    add_student(conn, "Amel", 10)
    add_student(conn, "Karim", 14)
    assert average_grade(conn) == 12.0
