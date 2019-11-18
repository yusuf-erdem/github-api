package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.IOException;
import java.util.Collection;

import static org.kohsuke.github.Previews.ZZZAX;

/**
 * The type GHBranchProtection.
 */
@SuppressFBWarnings(
        value = { "UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_FIELD", "NP_UNWRITTEN_FIELD",
                "URF_UNREAD_FIELD" },
        justification = "JSON API")
public class GHBranchProtection {
    private static final String REQUIRE_SIGNATURES_URI = "/required_signatures";

    @JsonProperty("enforce_admins")
    private EnforceAdmins enforceAdmins;

    private GitHub root;

    @JsonProperty("required_pull_request_reviews")
    private RequiredReviews requiredReviews;

    @JsonProperty("required_status_checks")
    private RequiredStatusChecks requiredStatusChecks;

    @JsonProperty
    private Restrictions restrictions;

    @JsonProperty
    private String url;

    /**
     * Enabled signed commits.
     *
     * @throws IOException
     *             the io exception
     */
    @Preview
    @Deprecated
    public void enabledSignedCommits() throws IOException {
        requester().method("POST").to(url + REQUIRE_SIGNATURES_URI, RequiredSignatures.class);
    }

    /**
     * Disable signed commits.
     *
     * @throws IOException
     *             the io exception
     */
    @Preview
    @Deprecated
    public void disableSignedCommits() throws IOException {
        requester().method("DELETE").to(url + REQUIRE_SIGNATURES_URI);
    }

    /**
     * Gets enforce admins.
     *
     * @return the enforce admins
     */
    public EnforceAdmins getEnforceAdmins() {
        return enforceAdmins;
    }

    /**
     * Gets required reviews.
     *
     * @return the required reviews
     */
    public RequiredReviews getRequiredReviews() {
        return requiredReviews;
    }

    /**
     * Gets required signatures.
     *
     * @return the required signatures
     * @throws IOException
     *             the io exception
     */
    @Preview
    @Deprecated
    public boolean getRequiredSignatures() throws IOException {
        return requester().method("GET").to(url + REQUIRE_SIGNATURES_URI, RequiredSignatures.class).enabled;
    }

    /**
     * Gets required status checks.
     *
     * @return the required status checks
     */
    public RequiredStatusChecks getRequiredStatusChecks() {
        return requiredStatusChecks;
    }

    /**
     * Gets restrictions.
     *
     * @return the restrictions
     */
    public Restrictions getRestrictions() {
        return restrictions;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    GHBranchProtection wrap(GHBranch branch) {
        this.root = branch.getRoot();
        return this;
    }

    private Requester requester() {
        return new Requester(root).withPreview(ZZZAX);
    }

    /**
     * The type EnforceAdmins.
     */
    public static class EnforceAdmins {
        @JsonProperty
        private boolean enabled;

        @JsonProperty
        private String url;

        /**
         * Gets url.
         *
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Is enabled boolean.
         *
         * @return the boolean
         */
        public boolean isEnabled() {
            return enabled;
        }
    }

    /**
     * The type RequiredReviews.
     */
    public static class RequiredReviews {
        @JsonProperty("dismissal_restrictions")
        private Restrictions dismissalRestriction;

        @JsonProperty("dismiss_stale_reviews")
        private boolean dismissStaleReviews;

        @JsonProperty("require_code_owner_reviews")
        private boolean requireCodeOwnerReviews;

        @JsonProperty("required_approving_review_count")
        private int requiredReviewers;

        @JsonProperty
        private String url;

        /**
         * Gets dismissal restrictions.
         *
         * @return the dismissal restrictions
         */
        public Restrictions getDismissalRestrictions() {
            return dismissalRestriction;
        }

        /**
         * Gets url.
         *
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Is dismiss stale reviews boolean.
         *
         * @return the boolean
         */
        public boolean isDismissStaleReviews() {
            return dismissStaleReviews;
        }

        /**
         * Is require code owner reviews boolean.
         *
         * @return the boolean
         */
        public boolean isRequireCodeOwnerReviews() {
            return requireCodeOwnerReviews;
        }

        /**
         * Gets required reviewers.
         *
         * @return the required reviewers
         */
        public int getRequiredReviewers() {
            return requiredReviewers;
        }
    }

    private static class RequiredSignatures {
        @JsonProperty
        private boolean enabled;

        @JsonProperty
        private String url;

        /**
         * Gets url.
         *
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Is enabled boolean.
         *
         * @return the boolean
         */
        public boolean isEnabled() {
            return enabled;
        }
    }

    /**
     * The type RequiredStatusChecks.
     */
    public static class RequiredStatusChecks {
        @JsonProperty
        private Collection<String> contexts;

        @JsonProperty
        private boolean strict;

        @JsonProperty
        private String url;

        /**
         * Gets contexts.
         *
         * @return the contexts
         */
        public Collection<String> getContexts() {
            return contexts;
        }

        /**
         * Gets url.
         *
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Is requires branch up to date boolean.
         *
         * @return the boolean
         */
        public boolean isRequiresBranchUpToDate() {
            return strict;
        }
    }

    /**
     * The type Restrictions.
     */
    public static class Restrictions {
        @JsonProperty
        private Collection<GHTeam> teams;

        @JsonProperty("teams_url")
        private String teamsUrl;

        @JsonProperty
        private String url;

        @JsonProperty
        private Collection<GHUser> users;

        @JsonProperty("users_url")
        private String usersUrl;

        /**
         * Gets teams.
         *
         * @return the teams
         */
        public Collection<GHTeam> getTeams() {
            return teams;
        }

        /**
         * Gets teams url.
         *
         * @return the teams url
         */
        public String getTeamsUrl() {
            return teamsUrl;
        }

        /**
         * Gets url.
         *
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Gets users.
         *
         * @return the users
         */
        public Collection<GHUser> getUsers() {
            return users;
        }

        /**
         * Gets users url.
         *
         * @return the users url
         */
        public String getUsersUrl() {
            return usersUrl;
        }
    }
}
