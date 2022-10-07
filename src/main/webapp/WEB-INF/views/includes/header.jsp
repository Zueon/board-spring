<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8"/>
<link rel="icon" href="%PUBLIC_URL%/favicon.ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta name="theme-color" content="#000000"/>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"
></script>
<script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"
></script>
<link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"
/>

<!-- web font -->
<!-- 1 -->
<link
        href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap"
        rel="stylesheet"
/>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<!-- my css -->
<style>
    body {
        font-family: "Open Sans", sans-serif;
    }

    .breadcrumb-item {
        font-size: 0.8em !important;
    }

    .ellipsis {
        display: block;
        width: 100%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis; /* 1 */
    }

    .board-table {
        table-layout: fixed;
    }

    .board-table .date {
        width: 100px;
    }

    .post-body {
        white-space: pre-line; /* 2 */
    }

    .post-info {
        font-size: 0.8em;
    }

    .btn {
        margin-right: 3px;
    }

</style>
