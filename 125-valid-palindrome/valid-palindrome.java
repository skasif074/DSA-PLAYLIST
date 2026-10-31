class Solution {
    public boolean isPalindrome(String s) {
        String str=filter(s);
        boolean rev=reverse(str);
        return rev;

    }

    public String filter(String str){
        String ser=str.toLowerCase();
        String ret="";
        for(int i=0;i<ser.length();i++){
            int ch=ser.charAt(i);
            if((ch>='a'&&ch<='z')||(ch>='0'&&ch<='9')){
                ret+=(char)ch;
            }
        }
        return ret;
    }

    public boolean reverse(String str){
        int i=0;
        int j=str.length()-1;
        int mid=j/2;
        while(i<=mid && j>=mid){
            char a=str.charAt(i);
            char b=str.charAt(j);
            if(a!=b){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}