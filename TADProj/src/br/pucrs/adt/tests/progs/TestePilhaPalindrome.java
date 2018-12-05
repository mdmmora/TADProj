package br.pucrs.adt.tests.progs;

import br.pucrs.adt.Stack;
import br.pucrs.adt.StackTAD;

public class TestePilhaPalindrome {

	public static boolean ehPalindrome(String s){
		boolean res = false;
		StackTAD<Character> pilha = new Stack<Character>();
		int i, ini, tam;
		char c;

		if ((s != null) && (!s.isEmpty())){
			tam = s.length();

			for(i = 0; i < tam/2; i++)
				pilha.push(s.charAt(i));

			ini = (tam % 2 == 0)?i:i+1;

			for (int j = ini; j < tam; j++) {
				c = pilha.pop();
				
				if (c != s.charAt(j))
					break;
			}
			
			res = pilha.isEmpty();
		}
		return res;
	}
	
		public static void main(String [] argc) {
			String str1 = "arara";
			String str2 = "urubu"; 
			String str3 = "papagaio";
			String str4 = "papaapap";
			String str5 = "";
			String str6 = null;
			
			System.out.println(str1 + " � palindrome? " + ehPalindrome(str1));
			System.out.println(str2 + " � palindrome? " + ehPalindrome(str2));
			System.out.println(str3 + " � palindrome? " + ehPalindrome(str3));
			System.out.println(str4 + " � palindrome? " + ehPalindrome(str4));
			System.out.println(str5 + " � palindrome? " + ehPalindrome(str5));
			System.out.println(str6 + " � palindrome? " + ehPalindrome(str6));
			
		}
}
