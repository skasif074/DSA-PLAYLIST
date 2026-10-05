class Solution {
    public boolean isPalindrome(int x) {
        int rev=0;
        int y=x;
        while(y>0){
            int a=y%10;
            rev=rev*10+a;
            y=y/10;
        }
        if(rev==x){
            return true;
        }
        return false;
    }
}