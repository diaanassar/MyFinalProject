  -- Populate Student table
  INSERT INTO Student (id, email, password, name, age) VALUES
                                                           (1, 'student1@example.com', 'password1', 'John Doe', 20),
                                                           (2, 'student2@example.com', 'password2', 'Alice Smith', 22),
                                                           (3, 'student3@example.com', 'password3', 'Bob Johnson', 21);
 
  -- Populate Course table
  INSERT INTO Course (id, name, instructor, student_id) VALUES
                                                            (1, 'Mathematics', 'Professor X', 1),
                                                            (2, 'Physics', 'Professor Y', 2),
                                                            (3, 'Computer Science', 'Professor Z', 3),
                                                            (4, 'Literature', 'Professor W', 1),
                                                            (5, 'History', 'Professor V', 2),
                                                            (6, 'Biology', 'Professor U', 3);
