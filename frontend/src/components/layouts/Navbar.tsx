import React from "react";
import { Button, Anchor } from "@mantine/core"

import "./styles/navbar.scss";

const Navbar: React.FC = (): JSX.Element => {
    return (
        <div className="navbar-container">
                <div className="logo"><a href={"/"}>Class Manager</a></div>
            <ul className="navlinks">
                <li className="news-letter">
                    <a href={`/newsletter`}>NewsLetter</a>
                </li>
                <li className="pricing">
                    <a href={`/pricing`}>Pricing</a>
                </li>
                <li className="about">
                    <a href={`/about`}>About</a>
                </li>
            </ul>
            <Button onClick={() => window.location.href = '/login'} ml="md" color="cyan" size="md" uppercase>
                Login
            </Button>

        </div>
    );
}


export default Navbar;
