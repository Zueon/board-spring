
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
    <div class="container">
        <div class="navbar-brand">My Website</div>
        <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="/" class="nav-link">
                        Home
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/about" class="nav-link">
                        About
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/posts" class="nav-link">
                        Board
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="/users" class="nav-link">
                        Users
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/users/new" class="nav-link">
                        Sign Up
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>