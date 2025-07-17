package tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            // リクエストURIからアクション名を取得（例: /kadai/SearchScore → SearchScore）
            String path = request.getServletPath().substring(1);
            String name = "action." + path + "Action"; // actionパッケージのクラス名

            // アクションクラスを動的にロード
            Class<?> clazz = Class.forName(name);
            Action action = (Action) clazz.getDeclaredConstructor().newInstance();

            // execute() メソッドを呼び出し、JSPパスを取得
            String view = action.execute(request, response);

            // 指定されたJSPにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
