import java.util.Scanner;

public class RomanTraining {
    public static int solution(String s) {
	int stringLength = s.length();
	int total = 0;
	int i = 0;
	while (i < stringLength) {

	    if (s.charAt(i) == 'M') {
		total += 1000;
		i++;
	    }
	    else if (s.charAt(i) == 'D') {
		total += 500;
		i++;
	    }
	    else if (s.charAt(i) == 'C') {
		
		if ( (i + 1) < stringLength ) {

			if (s.charAt(i + 1) == 'M') {
				total += 900;
				i += 2;
			}
			else if (s.charAt(i + 1) == 'D') {
				total += 400;
				i += 2;
			} else {
				total += 100;
				i++;
			}
		} else {
		    total += 100;
		    i++;
		}
	    }
	    else if (s.charAt(i) == 'L') {
		total += 50;
		i++;
	    }
	    else if (s.charAt(i) == 'X') {

		if ( (i + 1) < stringLength ) {
		    if (s.charAt(i + 1) == 'C') {
			total += 90;
			i += 2;
		    } 
		    else if (s.charAt(i + 1) == 'L') {
			total += 40;
			i += 2;
		    } else {
			total += 10;
			i++;
		    }
		} else {
		    total += 10;
		    i++;
		}
	    }
	    else if (s.charAt(i) == 'V') {
		total += 5;
		i++;
	    }
	    else if (s.charAt(i) == 'I') {
		    
		if ( (i + 1) < stringLength ) {
				
		    if (s.charAt(i + 1) == 'X') {
			total += 9;
			i += 2;
		    } 
		    else if (s.charAt(i + 1) == 'V') {
			total += 4;
			i += 2;
		    } else {
			total += 1;
			i++;
		    }
		} else {
		    total += 1;
		    i++;
		}
	    }
	}
	return total;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(solution(sc.nextLine()));
    }
}
