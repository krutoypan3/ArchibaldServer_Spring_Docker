
  Set WshShell = CreateObject("WScript.Shell")
  WshShell.Run chr(34) & "C:\DevProjects\ArchibaldServer\starter.bat" & Chr(34), 0
  Set WshShell = Nothing
  WScript.Quit