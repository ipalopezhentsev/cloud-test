{{- define "labels" -}}
labels:
    release: {{.Release.Name}}
    version: "{{.Chart.Version}}"
{{- end -}}