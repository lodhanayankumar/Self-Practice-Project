/**
 * 
 */
async function analyzeResume() {

    const resume =
        document.getElementById("resume").files[0];

    const jobDescription =
        document.getElementById(
            "jobDescription"
        ).value;

    if (!resume) {

        alert("Please select a resume.");

        return;
    }

    if (!jobDescription.trim()) {

        alert(
            "Please enter the job description."
        );

        return;
    }

    const formData =
        new FormData();

    formData.append(
        "resume",
        resume
    );

    formData.append(
        "jobDescription",
        jobDescription
    );

    document.getElementById(
        "loading"
    ).style.display = "block";

    document.getElementById(
        "result"
    ).style.display = "none";

    try {

        const response =
            await fetch(
                "/api/resumes/analyze",
                {
                    method: "POST",
                    body: formData
                }
            );

        const data =
            await response.json();

        if (!response.ok) {

            throw new Error(
                data.error || data
            );
        }

        showResult(data);

    } catch (error) {

        alert(
            "Error: "
            + error.message
        );

    } finally {

        document.getElementById(
            "loading"
        ).style.display = "none";
    }
}


function showResult(data) {

    document.getElementById(
        "result"
    ).style.display = "block";

    document.getElementById(
        "matchPercentage"
    ).innerText =
        data.matchPercentage + "%";

    fillList(
        "skills",
        data.extractedSkills
            ? data.extractedSkills.split(", ")
            : []
    );

    fillList(
        "missingSkills",
        data.missingSkills
            ? data.missingSkills.split(", ")
            : []
    );

    document.getElementById(
        "education"
    ).innerText =
        data.education || "Not detected";

    document.getElementById(
        "experience"
    ).innerText =
        data.experience || "Not detected";

    document.getElementById(
        "projects"
    ).innerText =
        data.projects || "Not detected";

    fillList(
        "suggestions",
        data.suggestions
            ? data.suggestions.split(" | ")
            : []
    );
}


function fillList(
    elementId,
    items
) {

    const element =
        document.getElementById(
            elementId
        );

    element.innerHTML = "";

    items.forEach(item => {

        const li =
            document.createElement("li");

        li.innerText = item;

        element.appendChild(li);
    });
}