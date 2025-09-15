-- Insert sample students
INSERT INTO student (first_name, last_name, email_address)
VALUES
    ('Tupac', 'Shakur', 'tupac@rap.com'),
    ('Andre', 'Young', 'dre@rap.com'),
    ('Calvin', 'Broadus', 'snoop@rap.com')
ON CONFLICT (email_address) DO NOTHING;

-- Insert sample math grades
INSERT INTO math_grade (grade, student_id)
SELECT 5.0, id FROM student WHERE email_address='tupac@rap.com'
ON CONFLICT DO NOTHING;

INSERT INTO math_grade (grade, student_id)
SELECT 4.0, id FROM student WHERE email_address='dre@rap.com'
ON CONFLICT DO NOTHING;

INSERT INTO math_grade (grade, student_id)
SELECT 3.5, id FROM student WHERE email_address='snoop@rap.com'
ON CONFLICT DO NOTHING;

-- Insert sample science grades
INSERT INTO science_grade (grade, student_id)
SELECT 3.0, id FROM student WHERE email_address='tupac@rap.com'
ON CONFLICT DO NOTHING;

INSERT INTO science_grade (grade, student_id)
SELECT 4.5, id FROM student WHERE email_address='dre@rap.com'
ON CONFLICT DO NOTHING;

INSERT INTO science_grade (grade, student_id)
SELECT 5.0, id FROM student WHERE email_address='snoop@rap.com'
ON CONFLICT DO NOTHING;

-- Insert sample history grades
INSERT INTO history_grade (grade, student_id)
SELECT 4.0, id FROM student WHERE email_address='tupac@rap.com'
ON CONFLICT DO NOTHING;

INSERT INTO history_grade (grade, student_id)
SELECT 5.0, id FROM student WHERE email_address='dre@rap.com'
ON CONFLICT DO NOTHING;

INSERT INTO history_grade (grade, student_id)
SELECT 3.5, id FROM student WHERE email_address='snoop@rap.com'
ON CONFLICT DO NOTHING;

