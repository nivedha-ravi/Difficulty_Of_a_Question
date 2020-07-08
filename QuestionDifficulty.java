import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class QuestionDifficulty {

  static double calculateTotalTime(HashMap<String,ArrayList> hm){
    double total_time=0.0;
    for(Map.Entry entry:hm.entrySet()){
      if(entry.getKey().equals("time_taken")){
        ArrayList<Double> temp=(ArrayList<Double>)entry.getValue();
        for(int i=0;i<temp.size();i++)
        total_time+=temp.get(i);
      }
    }
    return total_time;
  }

  static double calculateTotalChangesOrCompilations(HashMap<String,ArrayList> hm){
    int total_changes_or_compilations=0;
    for(Map.Entry entry:hm.entrySet()){
      if(entry.getKey().equals("number_of_changes_or_compilations")){
        ArrayList<Integer> temp=(ArrayList<Integer>)entry.getValue();
        for(int i=0;i<temp.size();i++)
          total_changes_or_compilations+=temp.get(i);
      }
    }
    return total_changes_or_compilations;
  }

  static double calculateTotalHints(HashMap<String,ArrayList> hm){
    int total_hints=0;
    for(Map.Entry entry:hm.entrySet()){
      if(entry.getKey().equals("number_of_hints")){
        ArrayList<Integer> temp=(ArrayList<Integer>)entry.getValue();
        for(int i=0;i<temp.size();i++)
          total_hints+=temp.get(i);
      }
    }
    return total_hints;
  }

  static int calculateDifficultyBasedOnTime(String question_type, String manual_difficulty, double avg_time){
    int difficulty_basedOnTime=0;
    if(question_type.equalsIgnoreCase("MCQ")){
      if(manual_difficulty.equalsIgnoreCase("Easy"))
        difficulty_basedOnTime=(avg_time<=1)?1:((avg_time<=1.5)?2:3);
      else if(manual_difficulty.equalsIgnoreCase("Medium"))
        difficulty_basedOnTime=(avg_time<=1.5)?1:((avg_time<=2)?2:3);
      else
        difficulty_basedOnTime=(avg_time<=2)?1:((avg_time<=2.5)?2:3);
    }
    else{
      if(manual_difficulty.equalsIgnoreCase("Easy"))
        difficulty_basedOnTime=(avg_time<=5)?1:((avg_time<=10)?2:3);
      else if(manual_difficulty.equalsIgnoreCase("Medium"))
        difficulty_basedOnTime=(avg_time<=15)?1:((avg_time<=25)?2:3);
      else
        difficulty_basedOnTime=(avg_time<=20)?1:((avg_time<=30)?2:3);
    }
    return difficulty_basedOnTime;
  }

  static int calculateDifficultyBasedOnChangesOrCompilations(String question_type, String manual_difficulty, double avg_changes_or_compilations){
    int difficulty_basedOnChangesOrCompilations=0;
    if(question_type.equalsIgnoreCase("MCQ"))
      difficulty_basedOnChangesOrCompilations=(avg_changes_or_compilations<2)?1:((avg_changes_or_compilations==2)?2:3);
    else{
      if(manual_difficulty.equalsIgnoreCase("Easy"))
        difficulty_basedOnChangesOrCompilations=(avg_changes_or_compilations<=3)?1:((avg_changes_or_compilations<=6)?2:3);
      else if(manual_difficulty.equalsIgnoreCase("Medium"))
        difficulty_basedOnChangesOrCompilations=(avg_changes_or_compilations<=6)?1:((avg_changes_or_compilations<=12)?2:3);
      else
        difficulty_basedOnChangesOrCompilations=(avg_changes_or_compilations<=10)?1:((avg_changes_or_compilations<=20)?2:3);
    }
    return difficulty_basedOnChangesOrCompilations;
  }

  static int calculateDifficultyBasedOnHints(String manual_difficulty, double avg_hints){
    int difficulty_basedOnHints=0;
    if(manual_difficulty.equalsIgnoreCase("Easy"))
      difficulty_basedOnHints=(avg_hints<2)?1:((avg_hints==2)?2:3);
    else if(manual_difficulty.equalsIgnoreCase("Medium"))
      difficulty_basedOnHints=(avg_hints<4)?1:((avg_hints==4)?2:3);
    else
      difficulty_basedOnHints=(avg_hints<6)?1:((avg_hints==6)?2:3);
    return difficulty_basedOnHints;
  }

  static int calculateDifficultyBasedOnFeedback(String feedback){
    int difficulty_basedOnFeedback=(feedback.equalsIgnoreCase("Easy"))?1:((feedback.equalsIgnoreCase("Medium"))?2:3);
    return difficulty_basedOnFeedback;
  }

  static int calculateDifficultyBasedOnMarks(int total_marks, double student_marks){
    double marks_rangeEasy=total_marks/3.0;
    double marks_rangeMedium=marks_rangeEasy*2.0;
    int difficulty_basedOnMarks=(student_marks<=marks_rangeEasy)?3:((student_marks<=marks_rangeMedium)?2:1);
    return difficulty_basedOnMarks;
  }

  static int calculateDifficultyBasedOnManualDifficulty(String manual_difficulty){
    int difficulty_basedOnManualDifficulty=(manual_difficulty.equalsIgnoreCase("Easy"))?1:((manual_difficulty.equalsIgnoreCase("Medium"))?2:3);
    return difficulty_basedOnManualDifficulty;
  }

  static String calculateQuestionDifficulty(ArrayList<Integer> final_difficulty){
    double easy=(Collections.frequency(final_difficulty,1))*1;
    double medium=(Collections.frequency(final_difficulty,2))*2;
    double hard=(Collections.frequency(final_difficulty,3))*3;
    String question_difficulty="";
    if(easy>medium && easy>hard)
      question_difficulty="Easy";
    else if(medium>easy && medium>hard)
      question_difficulty="Medium";
    else if(easy==2 && medium==4 && hard==6)
      question_difficulty="Medium";
    else if(hard>easy && hard>medium){
      if(Collections.frequency(final_difficulty,3)==Collections.frequency(final_difficulty,1))
        question_difficulty="Medium";
      else if(Collections.frequency(final_difficulty,3)>=(6/2))
        question_difficulty="Hard";
      else if(Collections.frequency(final_difficulty,2)>0)
        question_difficulty="Medium";
      else
        question_difficulty="Hard";
    }
    else if(easy==medium)
      question_difficulty="Easy";
    else if(easy==hard)
      question_difficulty="Medium";
    else if(medium==hard)
      question_difficulty="Hard";
    return question_difficulty;
  }

  public static void main(String[] args) throws Exception {
    try {
      File myObj = new File("inputset.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String input[]=myReader.nextLine().split(",");

        HashMap<String,ArrayList> hm=new HashMap<>();
        ArrayList<Double> dummy_one=new ArrayList<>();
        ArrayList<Integer> dummy_two=new ArrayList<>();
        ArrayList<Integer> dummy_three=new ArrayList<>();
        hm.put("time_taken",dummy_one);
        hm.put("number_of_changes_or_compilations",dummy_two);
        hm.put("number_of_hints",dummy_three);
        ArrayList<Integer> final_difficulty=new ArrayList<>();

        String question_type=input[0];
        String manual_difficulty=input[1];
        int total_students=Integer.parseInt(input[2]);
        int k=3;
        for(int i=0;i<total_students;i++){
          if(question_type.equals("MCQ")){
            for(int j=0;j<3;j++){
              if(j==0){
                ArrayList<Double> temp=hm.get("time_taken");
                temp.add(Double.parseDouble(input[k]));
                hm.put("time_taken",temp);
              }
              if(j==1){
                ArrayList<Integer> temp=hm.get("number_of_changes_or_compilations");
                temp.add(Integer.parseInt(input[k]));
                hm.put("number_of_changes_or_compilations",temp);
              }
              if(j==2){
                ArrayList<Integer> temp=hm.get("number_of_hints");
                temp.add(Integer.parseInt(input[k]));
                hm.put("number_of_hints",temp);
              }
              k++;
            }
          }
          else{
            for(int j=0;j<4;j++){
              String programming_language;
              if(j==0){
                ArrayList<Double> temp=hm.get("time_taken");
                temp.add(Double.parseDouble(input[k]));
                hm.put("time_taken",temp);
              }
              else if(j==1){
                ArrayList<Integer> temp=hm.get("number_of_changes_or_compilations");
                temp.add(Integer.parseInt(input[k]));
                hm.put("number_of_changes_or_compilations",temp);
              }
              else if(j==2){
                ArrayList<Integer> temp=hm.get("number_of_hints");
                temp.add(Integer.parseInt(input[k]));
                hm.put("number_of_hints",temp);
              }
              else if(j==3)
                programming_language=input[k];
              k++;
            }
          }
        }
        String feedback="";
        if(total_students>0)
          feedback=input[k++];
        int number_of_students_right=Integer.parseInt(input[k++]);
        int number_of_students_wrong=Integer.parseInt(input[k++]);
        int number_of_students_partially_correct=Integer.parseInt(input[k++]);
        int max_marks=Integer.parseInt(input[k++]);

        if(total_students>0){

          double total_time=calculateTotalTime(hm);
          int total_changes_or_compilations=(int)calculateTotalChangesOrCompilations(hm);
          int total_hints=(int)calculateTotalHints(hm);

          double avg_time=Double.parseDouble(String.format("%.2f",(total_time/(double)total_students)));
          double avg_changes_or_compilations=Double.parseDouble(String.format("%.2f",(total_changes_or_compilations/(double)total_students)));
          double avg_hints=Double.parseDouble(String.format("%.2f",(total_hints/(double)total_students)));

          //Calculating difficulty based on time taken and number of changes/compilations
          int difficulty_basedOnTime=calculateDifficultyBasedOnTime(question_type,manual_difficulty,avg_time);
          int difficulty_basedOnChangesOrCompilations=calculateDifficultyBasedOnChangesOrCompilations(question_type,manual_difficulty,avg_changes_or_compilations);
          final_difficulty.add(difficulty_basedOnTime);
          final_difficulty.add(difficulty_basedOnChangesOrCompilations);

          //Calculating difficulty based on hints
          int difficulty_basedOnHints=calculateDifficultyBasedOnHints(manual_difficulty,avg_hints);
          final_difficulty.add(difficulty_basedOnHints);

          //Calculating difficulty based on feedback
          int difficulty_basedOnFeedback=calculateDifficultyBasedOnFeedback(feedback);
          final_difficulty.add(difficulty_basedOnFeedback);

          //Calculating difficulty based on marks
          int total_marks=total_students*max_marks;
          double student_marks=(number_of_students_right*max_marks)+(number_of_students_wrong*0)+((number_of_students_partially_correct)*(max_marks/2));
          int difficulty_basedOnMarks=calculateDifficultyBasedOnMarks(total_marks,student_marks);
          final_difficulty.add(difficulty_basedOnMarks);
        }

        //Calculating dificulty based on manually assigned difficulty
        int difficulty_basedOnManualDifficulty=calculateDifficultyBasedOnManualDifficulty(manual_difficulty);
        final_difficulty.add(difficulty_basedOnManualDifficulty);

        //Calculating difficulty of the question
        String question_difficulty=calculateQuestionDifficulty(final_difficulty);

        System.out.println("Question difficulty : "+question_difficulty+"\n");

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
