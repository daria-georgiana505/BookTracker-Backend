package com.mpphw.springbootBackend.sanitizers;
import org.springframework.web.util.HtmlUtils;

public class InputSanitizer {
    public static String sanitizeHtml(String input) {
        // Escape HTML special characters
        return HtmlUtils.htmlEscape(input);
    }

    public static String sanitizeCommandLine(String input) {
        // Validate input and ensure it does not contain special characters or patterns
        // This is a basic example, and actual validation should be more thorough

        // Check for special characters commonly used in command injection attacks
        if (input.matches(".*[;&|`$<>(){}\\[\\]\"'*?~].*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for specific patterns or sequences that may indicate malicious intent
        if (input.matches(".*\\b\\w*(?:rm|shutdown|reboot|format|mkfs|dd|wget|curl|nc|telnet)\\b\\w*.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for absolute file paths that could lead to file manipulation or deletion
        if (input.matches(".*\\b(?:/|[a-zA-Z]:\\\\).*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to access system commands or environment variables
        if (input.matches(".*\\$\\{.*\\}.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to execute shell built-in commands or shell scripts
        if (input.matches(".*\\b(?:\\.sh|\\.bashrc|\\.profile)\\b.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for shell built-in commands
        if (input.matches(".*\\b(?:cd|echo|export|set)\\b.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for execution of network utilities
        if (input.matches(".*\\b(?:ping|netcat|nc|telnet)\\b.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for command-line options with potentially dangerous values
        if (input.matches(".*--[^\\s]+\\s+[\\w\\W]*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to use wildcard characters that could lead to unintended file operations
        if (input.matches(".*[*?].*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to use command substitution or backticks to execute commands
        if (input.matches(".*\\$\\(.*\\).*") || input.matches(".*`.*`.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to use redirection or piping to execute multiple commands
        if (input.matches(".*[<>|].*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts at absolute path traversal
        if (input.matches(".*\\.\\./.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for command injection patterns
        if (input.matches(".*\\b\\w+\\s*\\|\\s*\\w+.*") || input.matches(".*\\b\\w+\\s*;\\s*\\w+.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to execute operating system commands
        if (input.matches(".*\\b(?:cmd|powershell)\\b.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        // Check for attempts to execute JavaScript code
        if (input.matches(".*<script>.*</script>.*")) {
            throw new IllegalArgumentException("Invalid command-line input");
        }

        return input;
    }
}