package test;

 class Employee{

     public static void subArraySum(int input[], int sum,int size){

         int currentSum=input[0],i,start = 0;
         for(i=1;i<=size;i++){
            while(currentSum>sum){
                currentSum = currentSum-input[start];
                start++;
            }

            if(sum==currentSum){
                System.out.println("found between "+start+"and "+(i - 1));
                break;
            }

            currentSum=currentSum+input[i];
         }


     }


     public static void main(String args[])

    {
//        int[] input = {15,2,4,8,9,5,10,23};
//        input sum = 23;
//        int[] input = {10, 5, 2, 7, 1, 9};
//        int sum = 15;

//        int[] input = {1, 4, 20, 3, 10, 5};
//        int sum = 33;
//        int[] input = {33};
//        int sum = 33;
        int[] input = {100, 1};
        int sum = 1;
        subArraySum(input,sum,input.length);



    }
}

