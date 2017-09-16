import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;import java.text.SimpleDateFormat;
/**
 * A fix-lgd array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the lg of the array should be equal to the number of stored elements
 * after the element was added the lg of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
 public class StudentGroup implements StudentArrayOperation {

	 private int lg=0;
	private Student[] students;
       
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
           
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
			if(this.students.length<1|| lg<1)
				return null;
		return this.students;
	}

	@Override
	public void setStudents(Student[] std) {
		// Add your implementation here
		if(std==null) throw new IllegalArgumentException();
                  lg=std.length;
                students=students;
              
	}

	@Override
	public Student getStudent(int index) {
		// Add your implementation here
		if(index>=this.students.length||index<0) throw new IllegalArgumentException();
		return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		// Add your implementation here
                if(index>=students.length||index<0) throw new IllegalArgumentException();
                this.students[index]=student;
                return;
	}
        
        
	@Override
	public void addFirst(Student student) {
           if(student==null) throw new IllegalArgumentException();
	
		 int ol=students.length;
              
                if(lg+1>ol){   
               
                 int capacity=ol+1;
                  if(capacity<lg+1)
                        capacity=lg+1;
                  Student sd[]= students;
            
                  students = Arrays.copyOf(sd, capacity);
                }
                System.arraycopy(students, 0, students, 1, lg);
                students[0]=student;
               lg++;
	}

	@Override
	public void addLast(Student student) {
            if(student==null) throw new IllegalArgumentException();
	    int ol=students.length;
              
                if(lg+1>ol){   
                 int capacity=ol+1;
                  if(capacity<lg+1)
                        capacity=lg+1;
                  Student sd[]= students;
                  students = Arrays.copyOf(sd, capacity);
                }
            students[lg]=student;
            lg++;
    
        }

	@Override
	public void add(Student student, int index) {
            if(student==null) throw new IllegalArgumentException();
		// Add your implementation here
                if(index>=lg||index<0) throw new IllegalArgumentException();
                if(index==0) addFirst(student);
                else{
                    int ol=students.length;
              
                if(lg+1>ol){   
                 int capacity=ol+1;
                  if(capacity<lg+1)
                        capacity=lg+1;
                  Student sd[]= students;
      
                  students = Arrays.copyOf(sd, capacity);
                }
                    System.arraycopy(students, index, students, index+1, lg-index);
                    students[index]=student;
                   lg++; 
                }

	}

	@Override
	public void remove(int index) {
                if(index>=lg||index<0) throw new IllegalArgumentException();
                System.arraycopy(students, index+1, students, index, lg-index-1);
              lg--;
                students=Arrays.copyOfRange(students, 0, lg);
         ;
	}

	@Override
	public void remove(Student student) {
		// Add your implementation here
                if(student==null) throw new IllegalArgumentException();
                int j=0,k,index=getStudentId(student);
                if(index<0) throw new IllegalArgumentException("Student not exist");
                Student[] temp=new Student[lg-1];
         
                for( k=0;k<index;k++) temp[j++]=students[k];
                for(k=index+1;k<lg;k++) temp[j++]=students[k];
                students=temp;
               lg--;
	}

	@Override
	public void removeFromIndex(int index) {
	             if(index>=lg||index<0) throw new IllegalArgumentException();
                lg=index+1;
            students=  Arrays.copyOfRange(students, 0, index+1);
          
	}

	@Override
	public void removeFromElement(Student student) {
		// Add your implementation here

                 	// Add your implementation here
                 	       if(student==null) throw new IllegalArgumentException();
                 int index=getStudentId(student);
                 removeFromIndex(index);
           
                
	}

	@Override
	public void removeToIndex(int index) {
		// Add your implementation here
		
               if(index>=lg||index<0) 
                	throw new IllegalArgumentException();
                Student[] temp= new Student[lg-index+1];
                System.arraycopy(students, index, temp, 0, lg-index);
                lg=lg-index+1;
                students=temp;
              

	}

	@Override
	public void removeToElement(Student student) {
		
                if(student==null)
                	 throw new IllegalArgumentException();
                 int index=getStudentId(student);
                 removeToIndex(index);
	}

	@Override
	public void bubbleSort() {

              int n = lg;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (students[j].getFullName().compareTo(students[j+1].getFullName())>0)
                {

                    Student temp = students[j];
                    students[j] = students[j+1];
                    students[j+1] = temp;
                }
                
	}




	@Override
	public Student[] getByBirthDate(Date date) {
		// Add your implementation here
		if(date==null)throw new IllegalArgumentException();
 
                Student [] temp= new Student[lg];
                int index=0;
                for(int i=0;i<lg;i++) if(date.compareTo(students[i].getBirthDate())==0) temp[index++]=students[i];
      
                return Arrays.copyOfRange(temp, 0, index);
            
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		// Add your implementation here
		if(firstDate==null || lastDate==null)throw new IllegalArgumentException();
                    
                Student [] temp= new Student[lg];
                int index=0;   for(int i=0;i<lg;i++) if(students[i].getBirthDate().compareTo(firstDate)>=0&&students[i].getBirthDate().compareTo(lastDate)<=0) temp[index++]=students[i];
                if(index==0)return null;
                return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
             if(date==null) throw new IllegalArgumentException();
             if(days<0) days=0;
             Date newDate=increaseDays(date,days);
		return getBetweenBirthDates(date,newDate);
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		// Add your implementation here
		indexOfStudent--;
                if(indexOfStudent<0) throw new IllegalArgumentException();
                
		return calculateAge(students[indexOfStudent].getBirthDate());
		
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
	if(age<0) return null;
                Student[] temp= new Student[lg];
                int index=0;
                for(int i=0;i<lg;i++)
                    if(calculateAge(students[i].getBirthDate())==age) 
                    	temp[index++]=students[i];
                if(index==0) return null;
                return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		if(lg==0)
		return null;
		int index=0;
		double marks=-99999.0;
		for(int i=0;i<lg;i++)
			if(Double.compare(students[i].getAvgMark(),marks)>0) 
				marks=students[i].getAvgMark();
		Student[] temp=new Student[lg];
		for(int i=0;i<lg;i++) 
			if(Double.compare(students[i].getAvgMark(),marks)==0) 
				temp[index++]=students[i];
		if(index==0)
			 return null;
		return Arrays.copyOfRange(temp, 0, index);
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		if(student==null)
			 throw new IllegalArgumentException();
		int index=getStudentId(student);
		if(index==lg-1||index==-999)
		return null;
		return students[index+1];
	}
        
        
	public Date increaseDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
        private int getStudentId(Student student){
            for(int i=0;i<lg;i++){
                if(students[i].compareTo(student)==0) return i;
            }
            return -999;
        }
        public int calculateAge(Date birthDate)
   {
		      int years = 0;
		      int months = 0;
		      int days = 0;
		      //create calendar object for birth day
		      Calendar birthDay = Calendar.getInstance();
		      birthDay.setTimeInMillis(birthDate.getTime());
		      //create calendar object for current day
		      long currentTime = System.currentTimeMillis();
		      Calendar now = Calendar.getInstance();
		      now.setTimeInMillis(currentTime);
		      //Get difference between years
		      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		      int currMonth = now.get(Calendar.MONTH) + 1;
		      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
		      //Get difference between months
		      months = currMonth - birthMonth;
		      //if month difference is in negative then reduce years by one and calculate the number of months.
		      if (months < 0)
		      {
			 years--;
			 months = 12 - birthMonth + currMonth;
			 if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
			    months--;
		      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
		      {
			 years--;
			 months = 11;
		      }
		      //Calculate the days
		      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
			 days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
		      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
		      {
			 int today = now.get(Calendar.DAY_OF_MONTH);
			 now.add(Calendar.MONTH, -1);
			 days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
		      } else
		      {
			 days = 0;
			 if (months == 12)
			 {
			    years++;
			    months = 0;
			 }
		      }
		      //Create new Age object
		      return years;
		   }

		   	

}
