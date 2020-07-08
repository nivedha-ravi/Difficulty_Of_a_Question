# ALGORITHM TO CALCULATE THE DIFFICULTY OF A QUESTION

This algorithm is represented to calculate the difficulty of a question whether the assigned questions are **Easy, Medium, Hard.** Another important thing is that it provides accurate results and it is useful for online assessment platforms.

The Factors to calculate the difficulty of a question are as follows,

1. Question type -- MCQ, Fillup, Programming, Match the following
2. Manually assigned difficulty -- Easy, Medium, Hard
3. Total number of students who have attended this question
    a) Time taken by each student to answer this question
    b) Number of times the answer was changed if it is MCQ type question
    c) Number of times the program was compiled if it is programming question
    d) Number of hints used
    e) Programming language used if it is programming question (c, c++ , java etc)
4. Feedback given for this question by other students
5. Total number of students who have answered it right
6. Total number of students who have answered it wrong
7. Total number of students who have answered it partially correct
8. Maximum marks allocated for this question

-> Input is accepted in the form of a file that contains comma(,) seperated values of the above mentioned factors for each question.

-> The values for the above mentioned factors is retrieved from the input file.

-> A hashmap is created to store arraylist of values that contain time taken, number of changes or compilations and number of hints of each student.

-> The total values of time taken, number of changes or compilations and number of hints are calculated from the hashmap.

-> Average values are found for the same.

-> Difficulty based on each factor is calculated as below:

    --> Manually assigned difficulty

        Based on the manually assigned difficulty(Easy, Medium or Hard), the difficulty is set.
        The difficulty is added to an arraylist.

    --> Time taken

        Based on the type of question as MCQ(includes fillup and match the following) or Programming and also taking manually assigned difficulty into consideration, few values         are set and if the average time breaches these values, difficulty is set based on that.
        The difficulty is added to the arraylist.

    --> Number of changes or compilations

        Based on the type of question as MCQ(includes fillup and match the following) or Programming and also taking manually assigned difficulty into consideration few values           are set and if the average number of changes or compilations breaches the values, difficulty is set based on that.
        The difficulty is added to the arraylist.

     --> Number of hints

        Based on the manually assigned difficulty, a range is denoting each type of difficulty.
        The difficulty is added to the arraylist.

     --> Feedback

        Based on the feedback given by other students as easy, medium or hard, the difficulty is set.
        The difficulty is added to the arraylist.

     --> Marks

        The total marks of the question for all the students is calculated by taking a product of total students and maximum marks provided.
        The total student marks are calculated by taking a product of the number of students who got it right and the maximum marks, product of number of students who got it             wrong and 0, product of number of students who got it partially right and half of the maximum marks. All these products are summed up to give the student marks.
        The range for the difficulty of the students marks is set by dividing the total marks by 3.
        The difficulty is then calculated by where the student marks fit in the range.
        The difficulty is added to the arraylist.
-> Difficulty of the question

    The final difficulty of the question is calculated from the arraylist containing the difficulties of all the factors.
    It is done by comparing the frequencies of each of the difficulties in the arraylist.

    The difficulty is finally produced as output.
