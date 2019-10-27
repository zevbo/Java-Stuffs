public class Yeet {
  static int[][] bigTriangle = {{3},{7,4},{2,4,6},{8,5,9,3}};
  
  public static void main(String[] args){
    System.out.println(findMaxSum(bigTriangle));
  }
  
  public static int findMaxSum(int[][] triangle){
    if(triangle.length == 2){
      if(triangle[1][0] > triangle[1][1]){
        return triangle[0][0] + triangle[1][0];
      }else{
        return triangle[0][0] + triangle[1][1];
      }
    }else{
      int leftSum = findMaxSum(getSubTriangleFromRowCol(triangle, 1, 0));
      int rightSum = findMaxSum(getSubTriangleFromRowCol(triangle, 1, 1));
      if(leftSum > rightSum){
        return triangle[0][0] + leftSum;
      }else{
        return triangle[0][0] + rightSum;
      }
    }
  }
  
  public static int[][] getSubTriangleFromRowCol(int[][] superTriangle, int r, int c){
    int[][] subTriangle = new int[superTriangle.length - r][];
    for(int i = r; i < superTriangle.length; i++){
      subTriangle[i - r] = new int[superTriangle[i].length - c];
      for(int j = c; j < superTriangle[i].length; j++){
        subTriangle[i-r][j-c] = superTriangle[i][j];
      }
    }
    return subTriangle;
  }
}