package raisetech.student.management.controller.Handler;

public class Exception extends RuntimeException {

  public void TestException() {
    try {
      int result = 10 / 0;
    } catch (java.lang.Exception ex) {
      handleException(ex);
    }
  }

  public void StudentCourse(){
    try{
      String str = null;
      System.out.println(str.length());
    }catch (java.lang.Exception ex){
      handleException(ex);
    }
  }

  private void handleException(java.lang.Exception ex) {
    System.err.println("エラーが発生しました。" + ex.getMessage());
    ex.printStackTrace();
  }
}
