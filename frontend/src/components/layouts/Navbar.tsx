import React from "react";

import "./styles/navbar.scss";

const Navbar: React.FC = (): JSX.Element => {
    return (
        <div className="navbar-container">
            <div className="logo">Class Manager</div>
            <ul className="navlinks">
                <li>NewsLetter</li>
                <li>Pricing</li>
                <li>About</li>
            </ul>
        </div>
    );
}


export default Navbar;
