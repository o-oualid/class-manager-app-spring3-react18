import { createBrowserRouter, RouterProvider } from "react-router-dom";

import './App.scss';
import Home from "./pages/Home";
import Register from "./pages/Register";

function App() : JSX.Element {

  const router = createBrowserRouter([
      {
          path: "/",
          element: <Home />
      },
      {
          path: "/register",
          element: <Register />
      }
    ])

  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
