class Solution {
    public void reverseString(char[] s) {
        int i=0,j=s.length-1;
        int mid=j/2;
        while(i<=mid && j>=mid){
            char temp=s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }
}